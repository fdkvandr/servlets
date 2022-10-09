package com.http.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

    private static final String UNIQUE_ID = "user_id"; // Название нашей куки
    private static final AtomicInteger counter = new AtomicInteger(); // Счетчик новых клиентов. Используем потокобезопасный объект, поскольку Сервлет является singletone а запросов от клиентов может быть много

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        if (cookies == null || Arrays.stream(cookies)
                                     .filter(cookie -> UNIQUE_ID.equals(cookie.getName()))
                                     .findFirst()
                                     .isEmpty()) {
            Cookie cookie = new Cookie(UNIQUE_ID, "1"); // Смотрим, если у пользователя еще нету такой куки, то мы создаем новую куку, и возвращаем ее. Инкрементируем счетчик. Тогда, второй раз, когда он обратиться - кука уже будет и счетчик не синкрементируется -> посчитаем так количество уникальных пользователей
            cookie.setPath("/cookies"); // Если хотим, чтобы куки определялась и по другому домену
            cookie.setMaxAge(15 * 60); // Длительность жизни. По умолчанию "-1", что означает, что кука живет пока браузер не закроется

            resp.addCookie(cookie);
            counter.incrementAndGet();
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<b>%d</b>".formatted(counter.get()));
        }

    }
}
