/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 12.10.2019
 */
package ru.job4j.crud.filer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.crud.logic.SecurityService;
import ru.job4j.crud.logic.SecurityServiceImpl;
import ru.job4j.crud.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminRoleFilter implements Filter {

    private final SecurityService securityService = SecurityServiceImpl.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
        HttpServletRequest requestWrapper = new RequestWrapper(currentRequest);

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User loggedUser = securityService.getLoggedUser(requestWrapper.getSession());

        if (requestWrapper.getRequestURI().contains("/users/edit")) {
            if (requestWrapper.getMethod().equals("GET")) {
                int editedUserId = Integer.parseInt(requestWrapper.getParameter("id"));
                if (securityService.hasEditRight(requestWrapper.getSession(), new User.Builder().withId(editedUserId).build())) {
                    filterChain.doFilter(requestWrapper, servletResponse);
                } else {
                    response.sendRedirect("/");
                }
            } else if (requestWrapper.getMethod().equals("POST")) {
                Map<String, String> requsetParam = getMultipartParameters(requestWrapper);
                int editedUserId = Integer.parseInt(requsetParam.get("id"));
                if (securityService.hasEditRight(requestWrapper.getSession(), new User.Builder().withId(editedUserId).build())) {
                    filterChain.doFilter(requestWrapper, servletResponse);
                } else {
                    response.sendRedirect("/");
                }
            }
        } else {
            if (loggedUser != null && loggedUser.isAdmin()) {
                filterChain.doFilter(requestWrapper, servletResponse);
            } else {
                response.sendRedirect("/");
            }
        }
    }

    @Override
    public void destroy() {

    }

    private Map<String, String> getMultipartParameters(HttpServletRequest request) {
        Map<String, String> requsetParam = new HashMap<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = request.getSession().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            for (FileItem fileItem : upload.parseRequest(request)) {
                if (fileItem.isFormField()) {
                    requsetParam.put(fileItem.getFieldName(), fileItem.getString());
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return requsetParam;
    }
}
