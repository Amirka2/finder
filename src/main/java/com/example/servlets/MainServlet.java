package com.example.servlets;

import com.example.model.FileModel;
import com.example.service.DirectoryWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie loginCookie = getCookie("login", req);
        boolean isCookieExists = loginCookie != null;
        if (!isCookieExists) {
            resp.sendRedirect("/authorization");
        }

        String path;
        DirectoryWorker dw = new DirectoryWorker();

        path = req.getParameter("path");

        if (path != null) {
            File fPath = new File(path);
            path = "/tmp" + fPath.getCanonicalPath();
        }
        if (path == null || !path.contains(loginCookie.getValue()) || "".equals(path)) {
            path = "/tmp/" + loginCookie.getValue();
        }

        List<FileModel> content;
        content = dw.getList(path);

        String p = path.substring(4,path.length());

        resp.addCookie(loginCookie);
        req.setAttribute("content", content);
        req.setAttribute("path", p);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
    private Cookie getCookie(String cName,HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String cookieName = cName;
        if(cookies != null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }
}