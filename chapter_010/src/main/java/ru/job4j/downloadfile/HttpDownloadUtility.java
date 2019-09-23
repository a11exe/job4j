package ru.job4j.downloadfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.09.2019
 */
public class HttpDownloadUtility {

    /**
     *      * Downloads a file from a URL
     *      * @param fileURL HTTP URL of the file to be downloaded
     *      * @param saveDir path of the directory to save the file
     *      * @throws IOException
     *      
     */
    public static void downloadFile(String fileURL, String saveDir, int downloadLimitKbToSec)
            throws IOException, InterruptedException {

        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if ((responseCode == HttpURLConnection.HTTP_OK) && (checkSpeedLimit(downloadLimitKbToSec))) {

            String saveFilePath = getFileName(httpConn, fileURL, saveDir);

            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            long startTime = System.nanoTime();

            downloadWithLimit(inputStream, outputStream, downloadLimitKbToSec);

            outputStream.close();
            inputStream.close();

            long endTime = System.nanoTime();

            long duration = (endTime - startTime) / (1000000 * 1000);  // sec
            long fileSize = Files.size(Path.of(saveFilePath)) / 1024;
            long downloadSpeed = fileSize / duration;

            System.out.println(String.format("Downloading time: %s sec", duration));
            System.out.println(String.format("File size: %s Kb", fileSize));
            System.out.println(String.format("Downloading speed : %s Kb/sec", downloadSpeed));

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }

    private static String getFileName(HttpURLConnection httpConn, String fileURL, String saveDir) {
        String fileName = "";
        String disposition = httpConn.getHeaderField("Content-Disposition");

        if (disposition != null) {
            // extracts file name from header field
            int index = disposition.indexOf("filename=");
            if (index > 0) {
                fileName = disposition.substring(index + 10, disposition.length() - 1);
            }
        } else {
            // extracts file name from URL
            fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                    fileURL.length());
        }

        fileName = saveDir + File.separator + fileName;

        return fileName;
    }

    private static void downloadWithLimit(InputStream inputStream, OutputStream outputStream, int downloadLimitKbToSec) throws InterruptedException, IOException {

        int bytesRead = -1;
        long start = System.nanoTime();
        byte[] buffer = new byte[downloadLimitKbToSec * 1024];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
            long millis = ((System.nanoTime() - start) / 1000);
            long waitTime = 1000 - millis;
            if (waitTime > 0) {
                Thread.currentThread().sleep(waitTime);
            }
            start = System.nanoTime();
        }

    }

    private static boolean checkSpeedLimit(long downloadLimitKbToSec) {
        boolean result = true;
        if (downloadLimitKbToSec <= 0) {
            System.out.println("speed limit must be greate then 0");
            result = false;
        } else {
            System.out.println(String.format("speed limit %s kB/sec", downloadLimitKbToSec));
        }
        return result;
    }

}
