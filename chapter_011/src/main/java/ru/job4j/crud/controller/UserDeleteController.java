package ru.job4j.crud.controller;

import ru.job4j.crud.logic.ServletUtilImpl;
import ru.job4j.crud.logic.ServletUtil;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class UserDeleteController extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();
    private final ServletUtil servletUtil = ServletUtilImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        User user = logic.findById(id);
        String uploadPath = servletUtil.getUploadPath(request);

        if (logic.delete(user, uploadPath)) {
            response.sendRedirect("/");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().append("error delete user with id ").append(String.valueOf(id)).append(" not found");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}