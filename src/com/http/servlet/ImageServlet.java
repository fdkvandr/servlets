package com.http.servlet;


import com.http.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/images/*") // используем паттерн, чтобы все запросы начиная с images шли сюда
public class ImageServlet extends HttpServlet {

    private final ImageService imageService = ImageService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestUri = req.getRequestURI(); // получаем URI запроса
        String imagePath = requestUri.replace("/images", ""); // Получаем путь до картинки

        imageService.get(imagePath)
                    .ifPresentOrElse(image -> {
                        resp.setContentType("application/octet-stream");
                        writeImage(image, resp);
                    }, () -> resp.setStatus(404));

    }

    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try (image; OutputStream outputStream = resp.getOutputStream()) {
            int currentByte;
            while ((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }
}
