package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/** @noinspection ResultOfMethodCallIgnored*/
public class SearchTest {

    private final File root = new File(System.getProperty("java.io.tmpdir") + "root");
    private final List<String> dirs = List.of("dir", "dir2", "dir3");
    private final List<String> files = List.of("file.ext1", "file.ext2", "file.ext3", "file.ext4", "file.ext5", "file.ext6");

    private File createDir(String name) {
        File dir = new File(name);
        dir.mkdir();
        return dir;
    }

    private File createFile(String name) {
        File file = new File(name);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    @Before
    public void createTestDirsAndFiles() {

        root.mkdir();

        dirs.forEach(s -> {
            File dir = createDir(root.getAbsolutePath() + File.separator + s);
            files.forEach(f -> createFile(dir.getAbsolutePath() + File.separator + f));
        });
    }

    @After
    public void deleteTestDirsAndFiles() {
        Path pathToBeDeleted = root.toPath();

        try {
            Files.walk(pathToBeDeleted)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertFalse("Directory still exists",
                Files.exists(pathToBeDeleted));
    }

    @Test
    public void whenSearchAllExtShould18FilesFounded() {
        Search search = new Search();
        assertThat(search.files(root.getAbsolutePath(), List.of("ext1", "ext2", "ext3", "ext4", "ext5", "ext6")).size(), is(18));
    }

    @Test
    public void whenSearchEmptyExtShould0FilesFounded() {
        Search search = new Search();
        assertThat(search.files(root.getAbsolutePath(), List.of("")).size(), is(0));
    }


    @Test
    public void whenSearchExt1Should3FilesFounded() {
        Search search = new Search();
        assertThat(search.files(root.getAbsolutePath(), List.of("ext1")).size(), is(3));
    }


}