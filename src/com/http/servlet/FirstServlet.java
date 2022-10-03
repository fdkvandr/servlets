package com.http.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/first") // Аннотация, которая описывает наш сервлет
public class FirstServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html"); // Задаем заголовки в ответ
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1>Hello from First Servlet<h2>"); // Он сам добавит теги html, body и т.д., браузер автоматически преобразует наш body в html - страницу
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
