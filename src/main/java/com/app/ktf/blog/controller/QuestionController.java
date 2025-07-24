package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.QuestionEntity;
import com.app.ktf.blog.entity.QuizEntity;
import com.app.ktf.blog.service.QuestionService;
import com.app.ktf.blog.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    /**
     * List all questions
     */
    @GetMapping
    public String listQuestions(Model model) {
        List<QuestionEntity> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "questions/list"; // templates/questions/list.html
    }

    /**
     * Show form to create a new question
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("question", new QuestionEntity());
        List<QuizEntity> quizzes = quizService.findAll();
        model.addAttribute("quizzes", quizzes);
        return "questions/create"; // templates/questions/create.html
    }

    /**
     * Process form to create a new question
     */
    @PostMapping("/create")
    public String createQuestion(@ModelAttribute QuestionEntity question, Model model) {
        questionService.save(question);
        model.addAttribute("message", "Question created successfully!");
        return "redirect:/questions";
    }

    /**
     * Show form to edit an existing question
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        QuestionEntity question = questionService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid question ID: " + id));
        model.addAttribute("question", question);

        List<QuizEntity> quizzes = quizService.findAll();
        model.addAttribute("quizzes", quizzes);

        return "questions/edit"; // templates/questions/edit.html
    }

    /**
     * Process form to update an existing question
     */
    @PostMapping("/edit/{id}")
    public String updateQuestion(@PathVariable Long id, @ModelAttribute QuestionEntity question, Model model) {
        question.setId(id);
        questionService.save(question);
        model.addAttribute("message", "Question updated successfully!");
        return "redirect:/questions";
    }

    /**
     * View details of a question
     */
    @GetMapping("/view/{id}")
    public String viewQuestion(@PathVariable Long id, Model model) {
        QuestionEntity question = questionService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid question ID: " + id));
        model.addAttribute("question", question);
        return "questions/view"; // templates/questions/view.html
    }

    /**
     * Delete a question
     */
    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id, Model model) {
        questionService.deleteById(id);
        model.addAttribute("message", "Question deleted successfully!");
        return "redirect:/questions";
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
