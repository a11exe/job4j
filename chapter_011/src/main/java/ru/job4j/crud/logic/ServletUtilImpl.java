package ru.job4j.crud.logic;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.10.2019
 */
public class ServletUtilImpl implements ServletUtil {

    private final static ServletUtilImpl INSTANCE = new ServletUtilImpl();

    private ServletUtilImpl() {
    }

    public static ServletUtil getInstance() {
        return INSTANCE;
    }

    @Override
    public Map<String, String> getPostParams(HttpServletRequest request) throws FileUploadException {
        List<FileItem> items = getFileItems(request);
        Map<String, String> params = new HashMap<>();
        items.stream().filter(FileItem::isFormField).forEach(i->params.put(i.getFieldName(), i.getString()));
        return params;
    }

    @Override
    public FileItem getUploadedFileFromPostParametrs(HttpServletRequest request) throws FileUploadException {
        List<FileItem> items = getFileItems(request);
        return items.stream().filter(f->!f.isFormField()).findAny().orElse(null);
    }

    @Override
    public String getUploadPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/images/");
    }

    private List<FileItem> getFileItems(HttpServletRequest request) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = request.getSession().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        return upload.parseRequest(request);
    }

}
