/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 12.10.2019
 */
package ru.job4j.crud.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("loggedUser", null);
        resp.sendRedirect("/");
    }
}
