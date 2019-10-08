package ru.job4j.crud.presentation;

import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class UserServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().append(getHtmlTable(logic.findAll()));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private String getHtmlTable(List<User> users) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<head>");
        stringBuilder.append("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
        stringBuilder.append("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>");
        stringBuilder.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>");
        stringBuilder.append("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<div class=\"container\">");
        stringBuilder.append("<div class=\"col-5 offset-3\">");
        stringBuilder.append("<h2>Users</h2>");

        stringBuilder.append("<table class=\"table table-striped\" id=\"datatable\">\n")
                .append("            <thead>\n")
                .append("            <tr>\n")
                .append("                <th>id</th>\n")
                .append("                <th>name</th>\n")
                .append("                <th>login</th>\n")
                .append("                <th>email</th>\n")
                .append("                <th>createDate</th>\n")
                .append("                <th>edit</th>\n")
                .append("                <th>delete</th>\n")
                .append("            </tr>\n")
                .append("            </thead>");

        users.forEach(user -> stringBuilder
                .append("<tr>\n")
                .append("      <td>").append(user.getId()).append("</td>\n")
                .append("      <td>").append(user.getName()).append("</td>\n")
                .append("      <td>").append(user.getLogin()).append("</td>\n")
                .append("      <td>").append(user.getEmail()).append("</td>\n")
                .append("      <td>").append(user.getCreateDate()).append("</td>\n")
                .append("      <td><a href=\"http://localhost:8080/users/edit?id=").append(user.getId()).append("\" class=\"btn btn-link\" role=\"button\" aria-pressed=\"true\">edit</a></td>\n")
                .append("      <td><a href=\"http://localhost:8080/users/delete?id=").append(user.getId()).append("\" class=\"btn btn-danger\" role=\"button\" aria-pressed=\"true\">delete</a></td>\n")
                .append("    </tr>"));
        stringBuilder.append("</table>");
        stringBuilder.append("<a href=\"http://localhost:8080/users/create\" class=\"btn btn-success\" role=\"button\" aria-pressed=\"true\">Add user</a>");
        stringBuilder.append("</div>");
        stringBuilder.append("</div>");
        stringBuilder.append("</body></html>");

        return stringBuilder.toString();
    }
}