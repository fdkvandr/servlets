package com.http.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/flights");
//        req.setAttribute("1", "fiestObject");
//        requestDispatcher.forward(req, resp);

//        getServletContext().getRequestDispatcher("/flights").forward(req, resp);

//        req.getRequestDispatcher("/flights").include(req, resp);
//
//        resp.getWriter().write("dsf");

        resp.sendRedirect("/flights");

        System.out.println("sfsa");


    }
}
