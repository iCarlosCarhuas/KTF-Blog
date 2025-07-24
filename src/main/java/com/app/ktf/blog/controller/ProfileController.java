package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.security.UsuarioEntity;
import com.app.ktf.blog.service.UsuarioService;
import com.app.ktf.blog.util.CloudflareUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CloudflareUtil cloudflareUtil;

    @GetMapping("/")
    public String showProfile(HttpSession session, Model model) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);
        return "profile/dashboard";
    }

    @GetMapping("/edit")
    public String editProfileForm(HttpSession session, Model model) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);
        return "profile/edit";
    }

    @PostMapping("/update")
    public String updateProfile(
            @ModelAttribute UsuarioEntity formUser,
            @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile,
            @RequestParam(value = "avatarUrl", required = false) String avatarUrl,
            HttpSession session,
            Model model
    ) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("loggedInUser");

        user.setName(formUser.getName());
        user.setLastName(formUser.getLastName());
        user.setUsername(formUser.getUsername());
        user.setEmail(formUser.getEmail());

        if (avatarUrl != null && !avatarUrl.isEmpty()) {
            // URL was dropped
            user.setUserImage(avatarUrl);
        } else if (avatarFile != null && !avatarFile.isEmpty()) {
            // File was dropped, upload to Cloudflare
            String uploadedUrl = cloudflareUtil.uploadImage(avatarFile);
            user.setUserImage(uploadedUrl);
        }

        usuarioService.save(user);
        session.setAttribute("loggedInUser", user);

        model.addAttribute("message", "Profile updated successfully");
        return "redirect:/profile/";
    }


    @GetMapping("/filters")
    public String filters(HttpSession session, Model model) {
        UsuarioEntity user = (UsuarioEntity) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);
        return "profile/filters";
    }
}
