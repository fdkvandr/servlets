package com.http.filter;

import com.http.dto.UserDto;
import com.http.util.UrlPath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.http.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, IMAGES, LOCALE);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPablicPath(uri) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String prevPage = ((HttpServletResponse) servletResponse).getHeader("referer");// Говорит о том, с какой странички к нам пришел пользователь в фильтр, т.е. Previous page
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : LOGIN);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        UserDto user = (UserDto) ((HttpServletRequest) servletRequest).getSession()
                                                                      .getAttribute("user");
        return user != null;
    }

    private boolean isPablicPath(String uri) {
        return PUBLIC_PATH.stream()
                          .anyMatch(uri::startsWith);
    }
}
