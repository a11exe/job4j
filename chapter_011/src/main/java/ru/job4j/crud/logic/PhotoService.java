package ru.job4j.crud.logic;

import org.apache.commons.fileupload.FileItem;

public interface PhotoService {

    boolean loadFile(FileItem fileItem, String uploadPath, Integer id);

    boolean isFileExist(String uploadPath, Integer id);

    boolean deleteFile(String uploadPath, Integer id);

}
