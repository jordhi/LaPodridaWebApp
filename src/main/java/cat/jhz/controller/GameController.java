package cat.jhz.controller;

import cat.jhz.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(GameController.GAME)
public class GameController {
    public static final String GAME = "/game";

    @Autowired
    GameService gameService;

    /*@RequestMapping
    public String game(Model model) {
        return "game";
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public String findUsers(Model model) {
        model.addAttribute("users", gameService.findAll());
        return "game";
    }

}
