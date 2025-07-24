package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.PersonEntity;
import com.app.ktf.blog.service.PersonService;
import com.app.ktf.blog.util.CloudflareUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CloudflareUtil cloudflareUtil;

    @GetMapping("/{id}")
    public String viewProfile(@PathVariable Long id, Model model) {
        PersonEntity person = personService.findById(id).orElse(null);
        model.addAttribute("person", person);
        return "person/view";
    }

    @GetMapping("/edit/{id}")
    public String editProfileForm(@PathVariable Long id, Model model) {
        PersonEntity person = personService.findById(id).orElse(null);
        model.addAttribute("person", person);
        return "person/edit";
    }

    @PostMapping("/update")
    public String updateProfile(
            @ModelAttribute PersonEntity formPerson,
            @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile,
            @RequestParam(value = "avatarUrl", required = false) String avatarUrl,
            HttpSession session
    ) {
        PersonEntity person = personService.findById(formPerson.getId()).orElse(null);

        if(person != null) {
            person.setName(formPerson.getName());
            person.setLastName(formPerson.getLastName());
            person.setUsername(formPerson.getUsername());
            person.setBirthdate(formPerson.getBirthdate());

            if (avatarUrl != null && !avatarUrl.isEmpty()) {
                person.setUserImage(avatarUrl);
            } else if (avatarFile != null && !avatarFile.isEmpty()) {
                String uploadedUrl = cloudflareUtil.uploadImage(avatarFile);
                person.setUserImage(uploadedUrl);
            }

            personService.save(person);
        }

        return "redirect:/person/" + person.getId();
    }

}
