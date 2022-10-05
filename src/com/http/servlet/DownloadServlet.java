package com.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.beans.Encoder;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachment; filename=\"filename.json\"");
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

//        try (PrintWriter writer = resp.getWriter()) {
//            writer.write("Data from servlet");
//        }


//        byte[] bytes = Files.readAllBytes(Path.of("resources", " first.json")); // Так не получится
        try (ServletOutputStream writer = resp.getOutputStream();
             InputStream stream = DownloadServlet.class.getClassLoader().getResourceAsStream("first.json")) {
            byte[] bytes = stream.readAllBytes();
            resp.setContentLength(bytes.length);
            writer.write(bytes);
        }
    }
}
