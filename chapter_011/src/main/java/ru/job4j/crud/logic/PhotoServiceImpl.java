package ru.job4j.crud.logic;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PhotoServiceImpl implements PhotoService {

    private final static PhotoServiceImpl INSTANCE = new PhotoServiceImpl();

    private PhotoServiceImpl() {
    }

    public static PhotoServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean loadFile(FileItem fileItem, String uploadPath, Integer id) {
        boolean loaded = false;

        File folder = new File(uploadPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File(uploadPath + File.separator + id);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(IOUtils.toByteArray(fileItem.getInputStream()));
            loaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loaded;
    }

    @Override
    public boolean isFileExist(String uploadPath, Integer id) {
        return new File(uploadPath + File.separator + id).exists();
    }

    @Override
    public boolean deleteFile(String uploadPath, Integer id) {
        return new File(uploadPath + File.separator + id).delete();
    }
}
