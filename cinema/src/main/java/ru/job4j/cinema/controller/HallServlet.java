package ru.job4j.cinema.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public class HallServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    boolean newUser = !Arrays.stream(req.getCookies()).anyMatch(cookie -> cookie.getName().equals("sessioID"));
    if (newUser) {
      String sessionId = "" + Math.floor(Math.random() * 26) + LocalDateTime.now();
      Cookie ck = new Cookie("sessionId", sessionId);
      resp.addCookie(ck);
    }
    req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
  }
}
