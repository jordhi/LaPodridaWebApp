package cat.jhz.controller;

import cat.jhz.model.Game;
import cat.jhz.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(GameController.GAME)
public class GameController {
    public static final String GAME = "/game";
    public static final String USERS = "/users";

    private boolean gameStarted = false;
    private Game game;

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

    @PostMapping
    public String startGame(Model model) {
        //read all logged users
        model.addAttribute("users", gameService.findAll());
        if(gameStarted) {
            System.out.println("CONTINUE PLAY");
            model.addAttribute("torn", game.getTorn());
            //continuar amb el joc
            //canviar de torn
            //seguent repartiment
        }else {
            gameStarted = true;
            game = new Game(gameService.findAll());
            model.addAttribute("torn", game.getTorn());
            System.out.println("NEW START");
            //começar joc
            //repartir cartes
            //assignar torn
        }
        //TODO el game dona error en el if per canviar de color segons els torn del jugador
        return "game";
    }


}
