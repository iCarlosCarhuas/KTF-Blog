package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.security.UsuarioEntity;
import com.app.ktf.blog.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String showLogin() {
        return "security/login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        return usuarioService.validateLogin(email, password)
                .map(user -> {
                    session.setAttribute("loggedInUser", user);
                    model.addAttribute("user", user);
                    return "redirect:/posts"; // redirect to posts after login
                })
                .orElseGet(() -> {
                    model.addAttribute("error", "Invalid email or password");
                    return "security/login";
                });
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new UsuarioEntity());
        return "security/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute UsuarioEntity user, Model model) {
        if (usuarioService.findByCorreo(user.getEmail()).isPresent()) {
            model.addAttribute("error", "Email already registered");
            return "security/register";
        }

        //Generating user_publicID
        user.setPublicId(UUID.randomUUID().toString());


        usuarioService.save(user);
        model.addAttribute("message", "Registered successfully");
        return "redirect:/security/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/security/login";
    }
}
