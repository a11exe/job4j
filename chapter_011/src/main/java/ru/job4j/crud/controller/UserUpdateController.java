package ru.job4j.crud.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import ru.job4j.crud.logic.ServletUtil;
import ru.job4j.crud.logic.ServletUtilImpl;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.model.User;
import ru.job4j.crud.util.RequestWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class UserUpdateController extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();
    private final ServletUtil servletUtil = ServletUtilImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));

        User user = logic.findById(userId);

        if (user != null) {
            request.setAttribute("action", "edit");
            request.setAttribute("title", "Edit user");
            request.setAttribute("user", user);
            request.setAttribute("buttonName", "Edit user");
            getServletContext().getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().append("user with id ").append(String.valueOf(userId)).append(" not found");
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        RequestWrapper requestWrapper = new RequestWrapper(request);

        try {
            Map<String, String> params = servletUtil.getPostParams(requestWrapper);

            User user = new User(Integer.parseInt(params.get("id")), params.get("name"), params.get("login"), params.get("email"));
            FileItem fileItem = servletUtil.getUploadedFileFromPostParametrs(requestWrapper);
            String uploadPath = servletUtil.getUploadPath(requestWrapper);

            if (logic.update(user, fileItem, uploadPath)) {
                response.sendRedirect("/");
            } else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().append("error update user with id ").append(user.getId().toString()).append(" not found");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (FileUploadException e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().append("error ").append(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}