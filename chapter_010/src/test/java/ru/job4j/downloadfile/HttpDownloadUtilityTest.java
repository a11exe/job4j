package ru.job4j.downloadfile;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.09.2019
 */
public class HttpDownloadUtilityTest {

    @Test
    public void downloadFile() throws IOException, InterruptedException {

//        String fileName = "https://jdbc.postgresql.org/download/postgresql-42.2.8.jar";
        String fileName = "http://www.h2database.com/h2-2018-03-18.zip";
        Path tempDirWithPrefix = Files.createTempDirectory("tmp");
        System.out.println(tempDirWithPrefix.toAbsolutePath().toString());
        HttpDownloadUtility.downloadFile(fileName, tempDirWithPrefix.toAbsolutePath().toString(), 400);
    }
}