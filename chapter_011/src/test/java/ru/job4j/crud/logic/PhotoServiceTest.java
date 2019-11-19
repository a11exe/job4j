package ru.job4j.crud.logic;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.fileupload.FileItem;
import org.junit.Test;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.11.2019
 */
public class PhotoServiceTest {

  @Test
  public void loadFile() throws IOException {

    int fileId = 101;

    String tempFilePath = getTempDir();
    Path testFile = createTestFile(tempFilePath);
    Path loadedFile = getLoadedFilePath(tempFilePath, fileId);

    FileItem fileItemStub = mock(FileItem.class);
    when(fileItemStub.getInputStream()).thenReturn(Files.newInputStream(testFile));

    PhotoService photoService = PhotoServiceImpl.getInstance();

    assertTrue(Files.notExists(loadedFile));
    photoService.loadFile(fileItemStub, tempFilePath, fileId);
    assertTrue(Files.exists(loadedFile));
    assertThat(Files.readAllBytes(loadedFile), is(Files.readAllBytes(testFile)));
    Files.delete(loadedFile);
    Files.deleteIfExists(testFile);
  }


  private String getTempDir() throws IOException {
    File temp = File.createTempFile("temp-file-name", ".tmp");
    String absolutePath = temp.getAbsolutePath();
    return absolutePath.
            substring(0,absolutePath.lastIndexOf(File.separator));
  }

  private Path createTestFile(String tempFilePath) throws IOException {
    InputStream inputStream = PhotoServiceTest.class.getResourceAsStream("/me.jpg");
    Path testFile = Paths.get(tempFilePath, "test.jpg");
    Files.deleteIfExists(testFile);
    Files.copy(inputStream, testFile);
    return testFile;
  }

  private Path getLoadedFilePath(String tempFilePath, int id) throws IOException {
    Path loadedFile = Paths.get(tempFilePath, String.valueOf(id));
    Files.deleteIfExists(loadedFile);
    return loadedFile;
  }


}