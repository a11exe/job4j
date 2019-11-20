package ru.job4j.crud.controller.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.model.User;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.11.2019
 */
public class UserCreateJsonController extends HttpServlet {

  private final Validate logic = ValidateService.getInstance();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
    String requestData = reader.lines().collect(Collectors.joining());
    ObjectMapper mapper = new ObjectMapper();
    User user = mapper.readValue(requestData, User.class);
    user.setCreateDate(LocalDate.now());

    User userFromDB = logic.findByLogin(user.getLogin());

    if (userFromDB != null) {
      response.setContentType("text/html;charset=utf-8");
      response.getWriter().append("login is already busy");
      response.setStatus(HttpServletResponse.SC_CONFLICT);
    } else {
      if (logic.add(user, null, "")) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
      } else {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().append("error create user");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      }
    }

  }
}
