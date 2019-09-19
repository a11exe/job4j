package ru.job4j.cache;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 16.08.2019
 */
public class SoftReferenceFileCacheTest {

    @Test
    public void whenReadFirstTimeThenLoadFromFile() throws IOException {

        String expected = "John" + System.lineSeparator() + "Bob" + System.lineSeparator() + "Alex";
        File file = File.createTempFile("temp-file-name", ".txt");
        Files.write(file.toPath(), expected.getBytes());

        String name = file.getName();

        String absolutePath = file.getAbsolutePath();
        String tempFilePath = absolutePath.
                substring(0, absolutePath.lastIndexOf(File.separator));

        SoftReferenceFileCache softReferenceFileCache = new SoftReferenceFileCache(tempFilePath);
        assertThat(softReferenceFileCache.getValue(name), is(expected));

    }

    @Test
    public void whenReadSecondTimeThenGetFromCache() throws IOException {

        String expected = "John" + System.lineSeparator() + "Bob" + System.lineSeparator() + "Alex";
        File file = File.createTempFile("temp-file-name", ".txt");
        Files.write(file.toPath(), expected.getBytes());

        String name = file.getName();

        String absolutePath = file.getAbsolutePath();
        String tempFilePath = absolutePath.
                substring(0, absolutePath.lastIndexOf(File.separator));

        SoftReferenceFileCache softReferenceFileCache = new SoftReferenceFileCache(tempFilePath);
        assertThat(softReferenceFileCache.getValue(name), is(expected));
        Files.write(file.toPath(), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        assertThat(softReferenceFileCache.getValue(name), is(expected));
        assertThat(Files.readString(file.toPath()), is(""));

    }
}