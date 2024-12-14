package com.qsd.assignment.mancala.controller;

import com.qsd.assignment.mancala.service.MancalaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for Mancala board templates
 */
@Controller
@RequiredArgsConstructor
public class MancalaController {

    private final MancalaService mancalaService;

    @GetMapping("/")
    public String gameState(Model model) {

        model.addAttribute("mancalaGameState", mancalaService.getGameState());
        return "mancala";
    }

    @PostMapping("/makeMove")
    public String makeMove(@RequestParam(value = "selectedPit") String selectedPit, Model model) {

        mancalaService.makeMove(selectedPit);
        return "redirect:/";
    }

    @PostMapping("/resetGame")
    public String resetGame(Model model) {

        mancalaService.resetGame();
        return "redirect:/";
    }
}
