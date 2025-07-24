package com.app.ktf.blog.controller.security;

import com.app.ktf.blog.entity.security.UsuarioEntity;
import com.app.ktf.blog.service.security.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/security/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("userList", usuarioService.findAll());
        return "security/user/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("user", new UsuarioEntity());
        return "security/user/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute UsuarioEntity user) {
        usuarioService.save(user);
        return "redirect:/security/user/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", usuarioService.findById(id).orElseThrow());
        return "security/user/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return "redirect:/security/user/list";
    }
}
