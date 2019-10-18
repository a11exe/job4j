/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 12.10.2019
 */
package ru.job4j.crud.controller;

import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userNotFound", false);
        req.setAttribute("passwordIncorrect", false);
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = logic.findByLogin(login);

        if (user != null &&  user.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedUser", user);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("userNotFound", user == null);
            req.setAttribute("passwordIncorrect", user != null && !user.getPassword().equals(password));
            req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
