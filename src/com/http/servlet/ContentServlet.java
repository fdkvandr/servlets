package com.http.servlet;

import com.http.dto.FlightDto;
import com.http.service.FlightService;
import com.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flightDtos = flightService.findAll();
        req.setAttribute("flights", flightDtos);
        req.getSession()
           .setAttribute("flightMap", flightDtos.stream()
                                                .collect(toMap(FlightDto::getId, FlightDto::getDescriprion)));
        req.getRequestDispatcher(JspHelper.getPath("content"))
           .forward(req, resp); // Путь указываем начиная от папки web
    }
}
