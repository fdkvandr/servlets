package com.http.servlet;

import com.http.dto.CreateUserDto;
import com.http.entity.UserGender;
import com.http.entity.UserRole;
import com.http.exceptions.ValidationException;
import com.http.service.UserService;
import com.http.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/registration", name = "RegistrationServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024)
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getINSTANSE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("roles", UserRole.values());
        req.setAttribute("genders", UserGender.values());
        req.getRequestDispatcher(JspHelper.getPath("registration"))
           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CreateUserDto userDto = CreateUserDto.builder()
                                             .name(req.getParameter("name"))
                                             .birthday(req.getParameter("birthday"))
                                             .email(req.getParameter("email"))
                                             .image(req.getPart("image"))
                                             .password(req.getParameter("password"))
                                             .role(req.getParameter("role"))
                                             .gender(req.getParameter("gender"))
                                             .build();

        try {
            userService.create(userDto);
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }
}
