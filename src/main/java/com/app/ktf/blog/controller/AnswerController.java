package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.AnswerEntity;
import com.app.ktf.blog.entity.QuestionEntity;
import com.app.ktf.blog.service.AnswerService;
import com.app.ktf.blog.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    /**
     * List all answers
     */
    @GetMapping
    public String listAnswers(Model model) {
        List<AnswerEntity> answers = answerService.findAll();
        model.addAttribute("answers", answers);
        return "answers/list"; // templates/answers/list.html
    }

    /**
     * Show form to create a new answer
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("answer", new AnswerEntity());
        List<QuestionEntity> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "answers/create"; // templates/answers/create.html
    }

    /**
     * Process form to create a new answer
     */
    @PostMapping("/create")
    public String createAnswer(@ModelAttribute AnswerEntity answer, Model model) {
        answerService.save(answer);
        model.addAttribute("message", "Answer created successfully!");
        return "redirect:/answers";
    }

    /**
     * Show form to edit an existing answer
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AnswerEntity answer = answerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid answer ID: " + id));
        model.addAttribute("answer", answer);

        List<QuestionEntity> questions = questionService.findAll();
        model.addAttribute("questions", questions);

        return "answers/edit"; // templates/answers/edit.html
    }

    /**
     * Process form to update an existing answer
     */
    @PostMapping("/edit/{id}")
    public String updateAnswer(@PathVariable Long id, @ModelAttribute AnswerEntity answer, Model model) {
        answer.setId(id);
        answerService.save(answer);
        model.addAttribute("message", "Answer updated successfully!");
        return "redirect:/answers";
    }

    /**
     * View details of an answer
     */
    @GetMapping("/view/{id}")
    public String viewAnswer(@PathVariable Long id, Model model) {
        AnswerEntity answer = answerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid answer ID: " + id));
        model.addAttribute("answer", answer);
        return "answers/view"; // templates/answers/view.html
    }

    /**
     * Delete an answer
     */
    @GetMapping("/delete/{id}")
    public String deleteAnswer(@PathVariable Long id, Model model) {
        answerService.deleteById(id);
        model.addAttribute("message", "Answer deleted successfully!");
        return "redirect:/answers";
    }

    /**
     * Handle IllegalArgumentException globally within this controller
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error"; // templates/error.html
    }
}
