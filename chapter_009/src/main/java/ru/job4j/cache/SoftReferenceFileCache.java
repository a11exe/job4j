package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 16.08.2019
 */
public class SoftReferenceFileCache implements SoftReferenceCache<String, String> {

    private final HashMap<String, SoftReference<String>> cache = new HashMap<>();
    private final String pathFile;

    public SoftReferenceFileCache(String pathFile) {
        this.pathFile = pathFile;
    }

    @Override
    public String getValue(String key) {

        SoftReference<String> value = cache.get(key);

        if (value == null) {
            String fileData = "";
            try {
                fileData = Files.readString(Paths.get(this.pathFile + File.separator + key));
            } catch (IOException e) {
                e.printStackTrace();
            }
            value = new SoftReference<String>(fileData);
            cache.put(key, value);
        }

        return value.get();
    }
}
