package ru.job4j.wget;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.09.2019
 */
public class DownloaderTest {

    @Test
    public void checkDownloadSpeed() throws IOException, InterruptedException {

        String fileName = "http://www.h2database.com/h2-2018-03-18.zip";
        int maxSpeed = 400;
        Parameters param = new Parameters(
                new URL(fileName),
                maxSpeed);

        String saveFilePath = System.getProperty("java.io.tmpdir") + getFileName(param);

        long startTime = System.nanoTime();

        Thread thread = new Thread(new Downloader(param));
        thread.start();
        thread.join();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / (1000000 * 1000);  // sec
        long fileSize = Files.size(Path.of(saveFilePath)) / 1024;
        long downloadSpeed = fileSize / duration;

        System.out.println(String.format("Downloading time: %s sec", duration));
        System.out.println(String.format("File size: %s Kb", fileSize));
        System.out.println(String.format("Downloading speed : %s Kb/sec", downloadSpeed));

    }

    private String getFileName(Parameters param) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) param.url.openConnection();
        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.out.println("No file to download. Server replied HTTP code:" + responseCode);
            return "";
        }
        String fileName = "";
        String disposition = conn.getHeaderField("Content-Disposition");

        if (disposition != null) {
            // extracts file name from header field
            int index = disposition.indexOf("filename=");
            if (index > 0) {
                fileName = disposition.substring(index + 9);
            }
        } else {
            // extracts file name from URL
            fileName = param.url.getFile();
        }
        return fileName;
    }


}