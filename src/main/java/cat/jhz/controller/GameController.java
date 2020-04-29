package cat.jhz.controller;

import cat.jhz.model.Deck;
import cat.jhz.model.Game;
import cat.jhz.model.User;
import cat.jhz.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping(GameController.GAME)
public class GameController {
    public static final String GAME = "/game";
    public static final String USERS = "/users";

    private boolean gameStarted = false;
    private Game game;
    private Deck deck;
    private int doStart = 0;

    @Autowired
    GameService gameService;

    /*@RequestMapping
    public String game(Model model) {
        return "game";
    }*/

    @GetMapping
    public String findUsers(Model model) throws ExecutionException, InterruptedException {
        model.addAttribute("users", gameService.findAll().get());
        model.addAttribute("torn", new User());
        return "game";
    }

    @PostMapping
    public String startGame(Model model) throws ExecutionException, InterruptedException {
        //read all logged users
        model.addAttribute("users", gameService.findAll().get());
        model.addAttribute("start",doStart);
        if(gameService.findAll().get().size() > doStart) { //waiting start from all players
            doStart++;
            model.addAttribute("torn", new User());

            System.out.println("juagdors que han fet login: " + doStart);
        } else {
            //TODO refactor this part of code after add doStart
            if (gameStarted) {
                System.out.println("CONTINUE PLAY");
                model.addAttribute("torn", game.nextTorn());
                //continuar amb el joc

                //canviar de torn
                //seguent repartiment
            } else {
                gameStarted = true;
                //começar joc: nova baralla i nova partida
                deck = new Deck();
                game = new Game(gameService.findAll().get(), deck);
                model.addAttribute("torn", game.getTorn());
                System.out.println("NEW START");

                //TODO Test per verificar que els jugadors tenen les cartes que s'han assignat
                game.repartirCartes();
                sendDealingCardsToAPI();

            }
            model.addAttribute("repartir", game.getRepartir());
            game.nextRound();
        }

        return "game";
    }

    private void sendDealingCardsToAPI() {
        for(int j=0; j<game.getJugadors().size(); j++) {
            for(int i=0; i < game.getRepartir(); i++) {
                gameService.addCardToUser(
                        game.getJugadors().get(j),
                        game.getJugadors().get(j).getCartes().get(i));
            }
        }
    }


}
