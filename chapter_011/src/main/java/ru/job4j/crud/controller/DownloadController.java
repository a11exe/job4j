package ru.job4j.crud.controller;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("photoId");
        resp.setContentType("name=" + name);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        File file = new File(getServletContext().getRealPath("/images/") + File.separator + name);
        try (FileInputStream in = new FileInputStream(file)) {
            resp.getOutputStream().write(IOUtils.toByteArray(in));
        }
    }
}
