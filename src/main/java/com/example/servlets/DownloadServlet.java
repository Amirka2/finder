package com.example.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@WebServlet("/download/")
public class DownloadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getParameter("path");
        File file = new File(path);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        try (FileInputStream fileIn = new FileInputStream(file);
             OutputStream out = response.getOutputStream();) {
            byte[] outputByte = new byte[4096];
            int length;
            while ((length = fileIn.read(outputByte)) > 0)
                out.write(outputByte, 0, length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}