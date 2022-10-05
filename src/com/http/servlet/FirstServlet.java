package com.http.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@WebServlet("/first") // Аннотация, которая описывает наш сервлет
public class FirstServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getHeader("content-type");
        req.getHeader("user-agent"); // Чтобы узнать с какого устройства пришел запрос
        Enumeration<String> headers = req.getHeaderNames(); // Получаем Энумератор
        while (headers.hasMoreElements()) { // Смотрим, есть ли следующий элемент
            String header = headers.nextElement(); // Берем название хедера
            String headerValue = req.getHeader(header); // Берем значени хедера
        }

        resp.setContentType("text/html; charset=UTF-8"); // Задаем заголовки в ответ
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name()); // Можно передать в отдельном заголовке
        resp.setHeader("token", "12345"); // Задаем свой заголовок
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1>Hello from First Servlet. Привет!<h2>"); // Он сам добавит теги html, body и т.д., браузер автоматически преобразует наш body в html - страницу
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }


}
