package ru.job4j.wget;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.09.2019
 */
public class Downloader implements Runnable {
    private static final int BUFFER_SIZE = 4096;
    private Parameters param;

    public Downloader(Parameters param) {
        this.param = param;
    }


    @Override
    public void run() {
        try {
            HttpURLConnection conn = (HttpURLConnection) param.url.openConnection();
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("No file to download. Server replied HTTP code:" + responseCode);
                return;
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
            try (InputStream inputStream = conn.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(System.getProperty("java.io.tmpdir") + fileName)) {
                int bytesRead;
                int sum = 0;
                int readed = 0;
                long time = System.currentTimeMillis();
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    sum += bytesRead;
                    readed += bytesRead;
                    outputStream.write(buffer, 0, bytesRead);
                    if (sum >= param.maxSpeed) {
                        if ((System.currentTimeMillis() - time) < 1000L) {
                            try {
                                Thread.sleep(1000L - (System.currentTimeMillis() - time));
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }

                        }
                        System.out.printf("%s %d kb \r", fileName, readed / 1000);
                        sum = 0;
                        time = System.currentTimeMillis();
                    }
                }
                System.out.printf("%s %d kb finish", fileName, readed / 1000);
            } catch (IOException e) {
                throw e;
            } finally {
                conn.disconnect();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
