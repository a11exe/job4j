package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Pack project to zip archive
 *
 * -d - directory - source directory to archive [C:\project\my_project]
 * -e - exclude - file extensions to exclude divided by space [*.xml *.html]
 * -o - output - path to zip file [my_archive.zip]
 *
 *  java -jar pack.jar -d c:\project\job4j\ -e *.java -o project.zip
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.04.2019
 */
public class PackZip {

    /**
     * Args for zip
     *
     */
    static class Args {

        private final Map<String, Consumer<List<String>>> commands = new HashMap<>(Map.of(
                "-d", new Consumer<>() {
                    @Override
                    public void accept(List<String> list) {
                        directory = list.stream().findFirst().orElse("");
                    }
                },
                "-e", new Consumer<>() {
                    @Override
                    public void accept(List<String> list) {
                        exclude = list.stream().map(s -> Pattern.compile(s.replace("*.", ".\\.") + "$", Pattern.CASE_INSENSITIVE)).collect(Collectors.toList());
                    }
                },
                "-o", new Consumer<>() {
                    @Override
                    public void accept(List<String> list) {
                        output = list.stream().findFirst().orElse("");
                    }
                }
        ));

        String directory;
        String output;
        List<Pattern> exclude;

        public Args(String[] args) {

            List<String> param = new ArrayList<>();
            String key = null;

            for (String str: args
                    ) {
                if (isKey(str)) {
                    setKeyParam(key, param);
                    key = str;
                    param = new ArrayList<>();
                } else {
                    param.add(str);
                }
            }
            setKeyParam(key, param);
        }

        private boolean isKey(String str) {
            return commands.containsKey(str);
        }

        private void setKeyParam(String key, List<String> param) {
            if (commands.containsKey(key)) {
                commands.get(key).accept(param);
            }
        }
    }

    public static void main(String[] args) {

        PackZip packZip = new PackZip();
        Args param = new Args(args);
        try {
            packZip.pack(param);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean checkFileExtension(Path file, List<Pattern> ignore) {
        return ignore.stream().anyMatch(s -> checkFileExtension(file, s));
    }

    private boolean checkFileExtension(Path path, Pattern ext) {
        return ext.matcher(path.getFileName().toString()).find();
    }

    /**
     * Add project to archive
     *
     * @param param - params for zip archive
     * @throws IOException - exceptions
     */
    public void pack(Args param) throws IOException {

        String sourceDirPath = param.directory;
        String zipFilePath = param.output;
        List<Pattern> exclude = param.exclude;

        Path zipFilepath = Paths.get(zipFilePath);

        Files.deleteIfExists(zipFilepath);

        Path p = Files.createFile(zipFilepath);
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Path pp = Paths.get(sourceDirPath);
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path))
                    .filter(path -> !checkFileExtension(path, exclude))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                           e.printStackTrace();
                        }
                    });
        }
    }

}
