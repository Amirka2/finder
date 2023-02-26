package com.example.servlets;

import com.example.model.FileModel;
import com.example.service.DirectoryWorker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/"})
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DirectoryWorker dw = new DirectoryWorker();
        String path = req.getParameter("path");
        if (path.equals("")){
            path = "/";
        }
        String absolutePath = new File(path).getAbsolutePath();
        List<FileModel> content;
        if(path == null)
            content = new ArrayList<>();
        else content = dw.getList(path);
        req.setAttribute("content", content);
        req.setAttribute("path", absolutePath);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}