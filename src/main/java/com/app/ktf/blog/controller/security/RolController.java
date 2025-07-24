package com.app.ktf.blog.controller.security;

import com.app.ktf.blog.entity.security.RolEntity;
import com.app.ktf.blog.service.security.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/security/role")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/list")
    public String listRoles(Model model) {
        model.addAttribute("roleList", rolService.findAll());
        return "security/role/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("role", new RolEntity());
        return "security/role/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute RolEntity rol) {
        rolService.save(rol);
        return "redirect:/security/role/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("role", rolService.findById(id).orElseThrow());
        return "security/role/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        rolService.deleteById(id);
        return "redirect:/security/role/list";
    }
}
