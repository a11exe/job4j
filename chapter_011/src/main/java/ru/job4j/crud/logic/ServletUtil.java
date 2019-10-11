package ru.job4j.crud.logic;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.10.2019
 */
public interface ServletUtil {

    Map<String, String> getPostParams(HttpServletRequest request) throws FileUploadException;

    FileItem getUploadedFileFromPostParametrs(HttpServletRequest request) throws FileUploadException;

    String getUploadPath(HttpServletRequest request);

}
