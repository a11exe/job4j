package ru.job4j.crud.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import ru.job4j.crud.logic.ServletUtil;
import ru.job4j.crud.logic.ServletUtilImpl;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class UserCreateController extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();
    private final ServletUtil servletUtil = ServletUtilImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "create");
        request.setAttribute("title", "Create user");
        request.setAttribute("user", new User.Builder().build());
        request.setAttribute("buttonName", "Add user");
        request.setAttribute("roles", Role.values());
        getServletContext().getRequestDispatcher("/WEB-INF/views/user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        try {
            Map<String, String> params = servletUtil.getPostParams(request);

            User user = new User.Builder()
                    .withId(null)
                    .withName(params.get("name"))
                    .withLogin(params.get("login"))
                    .withEmail(params.get("email"))
                    .withPassword(params.get("password"))
                    .withCreateDate(LocalDate.now())
                    .withRole(Role.valueOf(params.get("role")))
                    .build();

            FileItem fileItem = servletUtil.getUploadedFileFromPostParametrs(request);
            String uploadPath = servletUtil.getUploadPath(request);

            if (logic.add(user, fileItem, uploadPath)) {
                response.sendRedirect("/");
            } else {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().append("error create user");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        } catch (FileUploadException e) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().append("error ").append(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}