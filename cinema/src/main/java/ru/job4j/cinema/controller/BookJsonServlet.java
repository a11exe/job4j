package ru.job4j.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.job4j.cinema.service.CinemaService;
import ru.job4j.cinema.service.CinemaServiceImpl;
import ru.job4j.model.Seat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 22.11.2019
 */
public class BookJsonServlet extends HttpServlet {

  CinemaService service = CinemaServiceImpl.getInstance();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
    String requestData = reader.lines().collect(Collectors.joining());
    ObjectMapper mapper = new ObjectMapper();
    Seat seat = mapper.readValue(requestData, Seat.class);

    String sessionId = "";
    Cookie cookie = Arrays.stream(req.getCookies())
            .filter(c -> c.getName().equals("sessionId"))
            .findFirst().orElse(null);
    if (cookie != null) {
      sessionId = cookie.getValue();
    }
    seat.setSessionId(sessionId);

    service.bookSeat(seat);
  }
}
