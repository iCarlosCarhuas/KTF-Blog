package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.QuizEntity;
import com.app.ktf.blog.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    /**
     * List all quizzes
     */
    @GetMapping
    public String listQuizzes(Model model) {
        List<QuizEntity> quizzes = quizService.findAll();
        model.addAttribute("quizzes", quizzes);
        return "quizzes/list"; // templates/quizzes/list.html
    }

    /**
     * Show form to create a new quiz
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("quiz", new QuizEntity());
        return "quizzes/create"; // templates/quizzes/create.html
    }

    /**
     * Process form to create a new quiz
     */
    @PostMapping("/create")
    public String createQuiz(@ModelAttribute QuizEntity quiz, Model model) {
        quizService.save(quiz);
        model.addAttribute("message", "Quiz created successfully!");
        return "redirect:/quizzes";
    }

    /**
     * Show form to edit an existing quiz
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        QuizEntity quiz = quizService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quiz ID: " + id));
        model.addAttribute("quiz", quiz);
        return "quizzes/edit"; // templates/quizzes/edit.html
    }

    /**
     * Process form to update an existing quiz
     */
    @PostMapping("/edit/{id}")
    public String updateQuiz(@PathVariable Long id, @ModelAttribute QuizEntity quiz, Model model) {
        quiz.setId(id);
        quizService.save(quiz);
        model.addAttribute("message", "Quiz updated successfully!");
        return "redirect:/quizzes";
    }

    /**
     * View details of a quiz
     */
    @GetMapping("/view/{id}")
    public String viewQuiz(@PathVariable Long id, Model model) {
        QuizEntity quiz = quizService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid quiz ID: " + id));
        model.addAttribute("quiz", quiz);
        return "quizzes/view"; // templates/quizzes/view.html
    }

    /**
     * Delete a quiz
     */
    @GetMapping("/delete/{id}")
    public String deleteQuiz(@PathVariable Long id, Model model) {
        quizService.deleteById(id);
        model.addAttribute("message", "Quiz deleted successfully!");
        return "redirect:/quizzes";
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
