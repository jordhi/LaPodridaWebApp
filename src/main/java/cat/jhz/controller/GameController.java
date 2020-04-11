package cat.jhz.controller;

import cat.jhz.model.User;
import cat.jhz.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(GameController.GAME)
public class GameController {
    public static final String GAME = "/game";
    public static final String USERS = "/users";

    @Autowired
    GameService gameService;

    /*@RequestMapping
    public String game(Model model) {
        return "game";
    }*/

    @GetMapping
    public String findUsers(Model model) {
        model.addAttribute("users", gameService.findAll());
        return "game";
    }

    /*@PostMapping
    public String startGame(Model model) {
        model.addAttribute("users", gameService.findAll());
        return "game";
    }*/




}
