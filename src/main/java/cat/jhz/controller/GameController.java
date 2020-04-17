package cat.jhz.controller;

import cat.jhz.model.Deck;
import cat.jhz.model.Game;
import cat.jhz.model.User;
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
    private Deck deck;

    @Autowired
    GameService gameService;

    /*@RequestMapping
    public String game(Model model) {
        return "game";
    }*/

    @GetMapping
    public String findUsers(Model model) {
        model.addAttribute("users", gameService.findAll());
        model.addAttribute("torn", new User());
        return "game";
    }

    @PostMapping
    public String startGame(Model model) {
        //read all logged users
        model.addAttribute("users", gameService.findAll());
        if(gameStarted) {
            System.out.println("CONTINUE PLAY");
            model.addAttribute("torn", game.nextTorn());
            //continuar amb el joc
            //canviar de torn
            //seguent repartiment
        }else {
            gameStarted = true;
            //começar joc: nova baralla i nova partida
            deck = new Deck();
            game = new Game(gameService.findAll(),deck);
            model.addAttribute("torn", game.getTorn());
            System.out.println("NEW START");

            //repartir cartes
            //TODO Test per verificar que els jugadors tenen les cartes que s'han assignat
            game.repartirCartes();
            //informar al server i a game.html de les cartes de cada jugador

            //¡¡¡¡¡¡ ARA NOMES POSA CARTES A AL JUGADOR QUE TE EL TORN !!!!!
            for(int i=0; i < game.getRepartir(); i++) {
                gameService.addCardToUser(game.getTorn(), game.getTorn().getCartes().get(i));
            }
            //assignar torn
        }
        return "game";
    }


}
