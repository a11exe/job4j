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
public class UserCreateServlet extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().append(getHtmlCreateForm());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Integer id = null;
        if (request.getParameter("id") != null) {
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String email = request.getParameter("email");

        User user = new User(id, name, login, email);

        // response.setStatus(result ? HttpServletResponse.SC_OK : HttpServletResponse.SC_BAD_REQUEST);
        if (logic.add(user)) {
            response.sendRedirect("http://localhost:8080/users");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().append("error create user");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private String getHtmlCreateForm() {
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
        stringBuilder.append("<h2>Create user</h2>");
        stringBuilder
                .append("<form method=\"post\" action=\"http://localhost:8080/users/create\">\n")
                .append("  <div class=\"form-group\">\n")
                .append("    <label for=\"InputName\">Name</label>\n")
                .append("    <input type=\"text\" name=\"name\" class=\"form-control\" id=\"InputName\" placeholder=\"Enter name\">\n")
                .append("  </div>\n")
                .append("  <div class=\"form-group\">\n")
                .append("    <label for=\"InputLogin\">Login</label>\n")
                .append("    <input type=\"text\" name=\"login\" class=\"form-control\" id=\"InputLogin\" placeholder=\"Enter login\">\n")
                .append("  </div>\n")
                .append("  <div class=\"form-group\">\n")
                .append("    <label for=\"InputEmail\">Email</label>\n")
                .append("    <input type=\"email\" name=\"email\" class=\"form-control\" id=\"exampleInputPassword1\" placeholder=\"Email\">\n")
                .append("  </div>\n")
                .append("<button type=\"submit\" class=\"btn btn-primary\">Create</button>")
                .append("</form>");

        stringBuilder.append("</div>");
        stringBuilder.append("</div>");
        stringBuilder.append("</body></html>");
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}