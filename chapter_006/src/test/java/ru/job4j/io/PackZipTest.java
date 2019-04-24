package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.04.2019
 */
public class PackZipTest {

    private final String fileSeparator = File.separator;
    private final String dir = System.getProperty("java.io.tmpdir");
    private final String zipName = dir + this.fileSeparator + "myproject.zip";
    private final String projectName = dir + this.fileSeparator + "project";

    @Before
    public void before() throws IOException {
        File project = createFile(projectName, true);
        File file1 = createFile(project + this.fileSeparator + "file1.txt", false);
        File file2 = createFile(project + this.fileSeparator + "file2.xml", false);
        File subDir1 = createFile(project + this.fileSeparator + "folder1", true);
        File file3 = createFile(subDir1 + this.fileSeparator + "file3.html", false);
        File file4 = createFile(subDir1 + this.fileSeparator + "file4.xml", false);
        File subDir2 = createFile(project + this.fileSeparator + "folder2", true);
        File file5 = createFile(subDir2 + this.fileSeparator + "file5.java", false);
        File file6 = createFile(subDir2 + this.fileSeparator + "file6.xml", false);
    }

    @After
    public void after() {
      recursiveDelete(new File(projectName));
    }

    @Test
    public void pack() throws IOException {

        PackZip packZip = new PackZip();
        PackZip.Args args = new PackZip.Args(("-d " + projectName + " -e *.xml *.html -o " + zipName).split(" "));

        assertThat(args.output, is(zipName));
        assertThat(args.directory, is(projectName));
        // assertThat(args.exclude, containsInAnyOrder(List.of(Pattern.compile(".\\.xml$"), Pattern.compile(".\\.html$"))));

        packZip.pack(args);

        File zipArchive = new File(zipName);

        assertTrue(zipArchive.exists());
        assertTrue(zipArchive.isFile());

        ZipInputStream zin = new ZipInputStream(new FileInputStream(zipArchive));

        ZipEntry entry;
        String name;
        int count = 0;
        while ((entry = zin.getNextEntry()) != null) {
            name = entry.getName(); // get file name
            assertFalse(name.endsWith(".html"));
            assertFalse(name.endsWith(".xml"));
            zin.closeEntry();
            count++;
        }

        assertThat(count, is(2));

    }

    private void recursiveDelete(File file) {
        //to end the recursive loop
        if (!file.exists()) {
            return;
        }
        //if directory, go inside and call recursively
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                //call recursively
                recursiveDelete(f);
            }
        }
        //call delete to delete files and empty directory
        file.delete();
    }

    private File createFile(String path, boolean dir) throws IOException {
        File file = new File(path);
        if (dir) {
            file.mkdir();
        } else {
            file.createNewFile();
        }
        return file;
    }
}