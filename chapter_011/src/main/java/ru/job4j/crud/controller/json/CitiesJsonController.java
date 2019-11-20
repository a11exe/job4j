package ru.job4j.crud.controller.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.model.City;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.11.2019
 */
public class CitiesJsonController extends HttpServlet {

  private final Validate logic = ValidateService.getInstance();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/json");
    List<City> cities = logic.findAllCities();
    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = mapper.writeValueAsString(cities);
    response.getWriter().write(jsonInString);
    response.getWriter().flush();
  }

}
