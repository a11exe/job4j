package ru.job4j.crud.presentation;

import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class UserDeleteServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        User user = logic.findById(id);

        if (logic.delete(user)) {
            response.sendRedirect("http://localhost:8080/users.jsp");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().append("error delete user with id ").append(String.valueOf(id)).append(" not found");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}