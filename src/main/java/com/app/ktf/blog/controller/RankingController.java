package com.app.ktf.blog.controller;

import com.app.ktf.blog.entity.RankingEntity;
import com.app.ktf.blog.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rankings")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    /**
     * List all rankings
     */
    @GetMapping
    public String listRankings(Model model) {
        List<RankingEntity> rankings = rankingService.findAll();
        model.addAttribute("rankings", rankings);
        return "rankings/list"; // templates/rankings/list.html
    }

    /**
     * Show form to create a new ranking
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("ranking", new RankingEntity());
        return "rankings/create"; // templates/rankings/create.html
    }

    /**
     * Process form to create a new ranking
     */
    @PostMapping("/create")
    public String createRanking(@ModelAttribute RankingEntity ranking, Model model) {
        rankingService.save(ranking);
        model.addAttribute("message", "Ranking created successfully!");
        return "redirect:/rankings";
    }

    /**
     * Show form to edit an existing ranking
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        RankingEntity ranking = rankingService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ranking ID: " + id));
        model.addAttribute("ranking", ranking);
        return "rankings/edit"; // templates/rankings/edit.html
    }

    /**
     * Process form to update an existing ranking
     */
    @PostMapping("/edit/{id}")
    public String updateRanking(@PathVariable Long id, @ModelAttribute RankingEntity ranking, Model model) {
        ranking.setId(id);
        rankingService.save(ranking);
        model.addAttribute("message", "Ranking updated successfully!");
        return "redirect:/rankings";
    }

    /**
     * View details of a ranking
     */
    @GetMapping("/view/{id}")
    public String viewRanking(@PathVariable Long id, Model model) {
        RankingEntity ranking = rankingService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ranking ID: " + id));
        model.addAttribute("ranking", ranking);
        return "rankings/view"; // templates/rankings/view.html
    }

    /**
     * Delete a ranking
     */
    @GetMapping("/delete/{id}")
    public String deleteRanking(@PathVariable Long id, Model model) {
        rankingService.deleteById(id);
        model.addAttribute("message", "Ranking deleted successfully!");
        return "redirect:/rankings";
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
