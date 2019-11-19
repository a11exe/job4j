package ru.job4j.crud.logic;

import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.10.2019
 */
public class ServletUtilStub implements ServletUtil {

    private final Map<String, String> postParams;
    private final FileItem uploadedFile;
    private final String uploadedPath;

    public ServletUtilStub(Map<String, String> postParams, FileItem uploadedFile, String uploadedPath) {
        this.postParams = postParams;
        this.uploadedFile = uploadedFile;
        this.uploadedPath = uploadedPath;
    }

    @Override
    public Map<String, String> getPostParams(HttpServletRequest request) {
        return postParams;
    }

    @Override
    public FileItem getUploadedFileFromPostParameters(HttpServletRequest request) {
        return this.uploadedFile;
    }

    @Override
    public String getUploadPath(HttpServletRequest request) {
        return this.uploadedPath;
    }

    @Override
    public List<FileItem> getFileItems(HttpServletRequest request) {
        return null;
    }
}
