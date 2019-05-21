package ru.job4j.search;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 16.05.2019
 */
public class SearchFilesTest {

    private final Path root = Paths.get(System.getProperty("java.io.tmpdir") + "root");
    private final List<String> files = List.of("file1.xml", "file2.xml", "file3.xml", "file4.xml", "file5.txt", "file6.ddd");
    private final Path outputFile = Path.of(root.toString(), "log.txt");

    private Path createFile(String name) {
        Path path = null;
        try {
            path = Files.createFile(Paths.get(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @Before
    public void createTestDirsAndFiles() {
        try {
            if (Files.exists(root)) {
                deleteTestDirsAndFiles();
            }
            Files.createDirectory(root);
            files.forEach(f -> createFile(root.toString() + File.separator + f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** @noinspection ResultOfMethodCallIgnored*/
    @After
    public void deleteTestDirsAndFiles() {
        try {
            Files.walk(root)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse("Directory still exists",
                Files.exists(root));
    }

    @Test
    public void whenMissionOptionShouldPrintErrorMsg() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.init("c:/ -n *.txt -m -o log.txt".split(" "));
        assertThat(byteArrayOutputStream.toString(), containsString("Missing required option: d"));
        System.setOut(System.out);
    }

    @Test
    public void whenUnknownKeyShouldPrintErrorMsg() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.init("-g c:/ *.txt -m -o log.txt".split(" "));
        assertThat(byteArrayOutputStream.toString(), containsString("Unrecognized option: -g"));
        System.setOut(System.out);
    }

    @Test
    public void whenMoreThanOneKeyFromSetShouldPrintErrorMsg() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.init("-d c:/ *.txt -m -o log.txt -r".split(" "));
        assertThat(byteArrayOutputStream.toString(), containsString("The option \'r\' was specified but an option from this group has already been selected: \'m\'"));
        System.setOut(System.out);
    }

    @Test
    public void whenSearchFilesMaskXMLInTempFolderShould4FilesFounded() throws IOException {
        List<String> expected = Arrays.asList("file1.xml", "file2.xml", "file3.xml", "file4.xml");
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.init(("-d " + root.toString() + " -n *.xml -m sd -o " + outputFile).split(" "));
        assertThat(Files.readAllLines(outputFile, Charset.forName("UTF-8")), is(expected));
    }

    @Test
    public void whenSearchFilesMaskFullNameXMLInTempFolderShould0FilesFounded() throws IOException {
        List<String> expected = new ArrayList<>();
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.init(("-d " + root.toString() + " -n *.xml -f sd -o " + outputFile).split(" "));
        assertThat(Files.readAllLines(outputFile, Charset.forName("UTF-8")), is(expected));
    }

    @Test
    public void whenSearchFilesFullNameInTempFolderShould1FileFounded() throws IOException {
        List<String> expected = Collections.singletonList("file2.xml");
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.init(("-d " + root.toString() + " -n file2.xml -f sd -o " + outputFile).split(" "));
        assertThat(Files.readAllLines(outputFile, Charset.forName("UTF-8")), is(expected));
    }

    @Test
    public void whenSearchFilesPatternInTempFolderShould1FileFounded() throws IOException {
        List<String> expected = Collections.singletonList("file5.txt");
        SearchFiles searchFiles = new SearchFiles();
        searchFiles.init(("-d " + root.toString() + " -n .+txt -r sd -o " + outputFile).split(" "));
        assertThat(Files.readAllLines(outputFile, Charset.forName("UTF-8")), is(expected));
    }

}