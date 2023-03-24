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
            String email = req.getParameter("email");
            HttpSession session = req.getSession();

            if (!AuthService.isUserExists(login) && email != null) {
                Cookie emailCookie = new Cookie("email", email);
                User newUser = new User(login, password, email);
                AuthService.addUser(newUser);
                session.setAttribute("user", newUser);

                resp.addCookie(emailCookie);
                resp.sendRedirect("/authorization");
            } else if (AuthService.isUserExists(login)){
                User user = AuthService.getUser(login);
                session.setAttribute("user", user);
                AuthService.setUserActive(user);

                Cookie loginCookie = new Cookie("login", login);
                Cookie passwordCookie = new Cookie("password", password);

                loginCookie.setMaxAge(-1);
                passwordCookie.setMaxAge(-1);
                resp.addCookie(loginCookie);
                resp.addCookie(passwordCookie);
                resp.sendRedirect("/");
            }

        }

    }
}
