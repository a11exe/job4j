package ru.job4j.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.job4j.cinema.service.CinemaService;
import ru.job4j.cinema.service.CinemaServiceImpl;
import ru.job4j.model.Hall;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public class HallJsonServlet extends HttpServlet {

  CinemaService service = CinemaServiceImpl.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    resp.setContentType("text/json");

    String sessionId = "";
    Cookie cookie = Arrays.stream(req.getCookies())
            .filter(c -> c.getName().equals("sessionId"))
            .findFirst().orElse(null);
    if (cookie != null) {
      sessionId = cookie.getValue();
    }

    Hall hall = service.getHall(sessionId);
    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(hall);
    resp.getWriter().write(jsonInString);
    resp.getWriter().flush();
  }
}
