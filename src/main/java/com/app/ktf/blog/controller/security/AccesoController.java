package com.app.ktf.blog.controller.security;

import com.app.ktf.blog.entity.security.AccesoEntity;
import com.app.ktf.blog.service.security.AccesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/security/access")
public class AccesoController {

    @Autowired
    private AccesoService accesoService;

    @GetMapping("/list")
    public String listAccesses(Model model) {
        model.addAttribute("accessList", accesoService.findAll());
        return "security/access/list"; // adapt your Thymeleaf path
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("access", new AccesoEntity());
        return "security/access/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute AccesoEntity acceso) {
        accesoService.save(acceso);
        return "redirect:/security/access/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("access", accesoService.findById(id).orElseThrow());
        return "security/access/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        accesoService.deleteById(id);
        return "redirect:/security/access/list";
    }
}
