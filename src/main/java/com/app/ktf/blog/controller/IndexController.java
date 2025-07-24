package com.app.ktf.blog.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index(HttpSession session) {
        if (session.getAttribute("loggedInUser") == null) {
            return "index"; // public index.html
        } else {
            return "redirect:/posts"; // redirect to posts list
        }
    }
}
