package com.app.ktf.blog.controller.security;

import com.app.ktf.blog.entity.security.PerfilEntity;
import com.app.ktf.blog.service.security.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/security/profile")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/list")
    public String listProfiles(Model model) {
        model.addAttribute("profileList", perfilService.findAll());
        return "security/profile/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("profile", new PerfilEntity());
        return "security/profile/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute PerfilEntity perfil) {
        perfilService.save(perfil);
        return "redirect:/security/profile/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("profile", perfilService.findById(id).orElseThrow());
        return "security/profile/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        perfilService.deleteById(id);
        return "redirect:/security/profile/list";
    }
}
