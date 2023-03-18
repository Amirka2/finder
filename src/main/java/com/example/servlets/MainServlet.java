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
import java.util.List;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie loginCookie = getCookie("login", req);
        boolean isCookieExists = loginCookie != null;
        if (!isCookieExists) {
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        }

        DirectoryWorker dw = new DirectoryWorker();
        String path = req.getParameter("path");
        if (path == null || path.equals("")){
            path = "/";
        }
        String absolutePath = new File(path).getAbsolutePath();
        List<FileModel> content;
        content = dw.getList(path);

        resp.addCookie(loginCookie);
        req.setAttribute("content", content);
        req.setAttribute("path", absolutePath);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
    private Cookie getCookie(String cName,HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String cookieName = cName;
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }
}