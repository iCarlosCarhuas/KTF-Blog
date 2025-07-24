package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.security.UsuarioEntity;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@ControllerAdvice
public class SecurityInterceptor {

    @ModelAttribute
    public void checkSession(HttpSession session, HttpServletResponse response) throws IOException {
        String url = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRequestURI();

        UsuarioEntity loggedInUser = (UsuarioEntity) session.getAttribute("loggedInUser");

        if (loggedInUser == null &&
                !url.contains("login") &&
                !url.contains("register") &&
                !url.equals("/") &&
                !url.equals("/index")) {
            response.sendRedirect("/security/login");
        }

        if (url.startsWith("/admin") &&
                (loggedInUser == null || !loggedInUser.getRol().getNombreRol().equals("ROLE_ADMIN"))) {
            response.sendRedirect("/posts");
        }
    }
}
