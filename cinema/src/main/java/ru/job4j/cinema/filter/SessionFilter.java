/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.11.2019
 */
package ru.job4j.cinema.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        boolean newUser = true;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            newUser = Arrays.stream(request.getCookies())
                .noneMatch(cookie -> cookie.getName().equals("sessionId"));
        }
        if (newUser) {
            String sessionId = "" + Math.floor(Math.random() * 26) + LocalDateTime.now();
            Cookie ck = new Cookie("sessionId", sessionId);
            ck.setMaxAge(60*60*24);
            response.addCookie(ck);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}