package ru.job4j.io;

import java.io.File;
import java.util.*;


public class Search {

    /**
     * Return list of all files with extension
     *
     * @param parent parent folder start from
     * @param exts   files extension
     * @return list of files
     */
    List<File> files(String parent, List<String> exts) {
        Queue<File> files;
        List<File> result = new ArrayList<>();
        files = new LinkedList<>();

        File file = new File(parent);
        files.offer(file);
        while (files.size() > 0) {
            file = files.poll();
            if (file == null) {
                continue;
            }
            if (file.isDirectory()) {
                Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(files::offer);
            } else {
                if (checkFileExtension(file, exts)) {
                    result.add(file);
                }
            }
        }
        return result;
    }

    /**
     * check file extension
     * @param file file
     * @param extensions list extensions
     * @return is file ext in list
     */
    private boolean checkFileExtension(File file, List<String> extensions) {
        return extensions.stream().anyMatch(s -> checkFileExtension(file, s));
    }

    /**
     * check file extension
     * @param file file
     * @param ext extension
     * @return is file has this extension
     */
    private boolean checkFileExtension(File file, String ext) {
        return getExtension(file).equals(ext);
    }

    /**
     * Return file extension
     * @param file input file
     * @return file extension
     */
    private String getExtension(File file) {
        String fileName = file.getName();
        String[] arr = fileName.split("\\.");
        return arr[arr.length - 1];
    }

}
