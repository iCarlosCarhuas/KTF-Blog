package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.CategoryEntity;
import com.app.ktf.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoriaService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoriaService.findAll());
        return "categories/list";
    }

    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        model.addAttribute("category", new CategoryEntity());
        return "categories/new";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute CategoryEntity category) {
        categoriaService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        CategoryEntity category = categoriaService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category id:" + id));
        model.addAttribute("category", category);
        return "categories/edit";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute CategoryEntity category) {
        categoriaService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return "redirect:/categories";
    }
}
