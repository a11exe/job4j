package ru.job4j.crud.controller.json;

import java.io.IOException;
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
public class UserDeleteJsonController  extends HttpServlet {
  private final Validate logic = ValidateService.getInstance();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));

    User user = logic.findById(id);
    String uploadPath = getServletContext().getRealPath("/images/");

    if (logic.delete(user, uploadPath)) {
      response.setContentType("text/html;charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      response.setContentType("text/html;charset=utf-8");
      response.getWriter().append("error delete user with id ").append(String.valueOf(id)).append(" not found");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
