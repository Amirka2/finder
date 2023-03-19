package com.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/authorization")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isLoggedIn = false;
        req.setAttribute("isLoggedIn", isLoggedIn);
        req.getRequestDispatcher("auth.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("submit".equals(action)) {
            Cookie login = new Cookie("login", req.getParameter("login"));
            Cookie password = new Cookie("password", req.getParameter("password"));
            login.setMaxAge(-1);
            password.setMaxAge(-1);
            resp.addCookie(login);
            resp.addCookie(password);
            if (req.getAttribute("email") != null) {
                Cookie email = new Cookie("email", req.getParameter("email"));
                email.setMaxAge(-1);
                resp.addCookie(email);
                resp.sendRedirect("/authorization");
            } else {
                resp.sendRedirect("/");
            }
        }

    }
}
