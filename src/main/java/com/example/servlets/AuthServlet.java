package com.example.servlets;

import com.example.model.User;
import com.example.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            if (!AuthService.isUserExists(login, password)) {
                resp.sendRedirect("/authorization");
            }

            User user = AuthService.getUser(login, password);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            AuthService.setUserActive(user);

            Cookie loginCookie = new Cookie("login", login);
            Cookie passwordCookie = new Cookie("password", password);

            loginCookie.setMaxAge(-1);
            passwordCookie.setMaxAge(-1);
            resp.addCookie(loginCookie);
            resp.addCookie(passwordCookie);
            if (req.getParameter("email") != null) {
                String email = req.getParameter("email");
                Cookie emailCookie = new Cookie("email", email);
                User newUser = new User(login, password, email);
                AuthService.addUser(newUser);
                session.setAttribute("user", user);

                resp.addCookie(emailCookie);
                resp.sendRedirect("/authorization");
            } else {
                resp.sendRedirect("/");
            }
        }

    }
}
