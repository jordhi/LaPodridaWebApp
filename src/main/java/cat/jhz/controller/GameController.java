package cat.jhz.controller;

import cat.jhz.model.Deck;
import cat.jhz.model.Game;
import cat.jhz.model.User;
import cat.jhz.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String findUsers(Model model) {
        model.addAttribute("users", gameService.findAll());
        model.addAttribute("torn", new User());
        return "game";
    }

    @PostMapping
    public synchronized String startGame(Model model) {
        //read all logged users
        List<User> userList = gameService.findAll();
        model.addAttribute("users", userList);

        while(userList.size() < 2) { //waiting start from 2 players
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userList = gameService.findAll();
        }
        // If the second player get into then he can notify to the others in wait
        notifyAll();
        System.out.println("juagdors que han fet login: " + userList.size());
        gameStarted = true;

        //começar joc: nova baralla i nova partida
        // CODI PROVISIONAL NOMÉS PER COMPROVACIONS
        //TODO Cal gestionar bé els torns i esborrar les cartes de la baralla només quan un jugador obté les cartes però refer la baralla al seguent torn

        if(doStart < userList.size()) {
            deck = new Deck();
            game = new Game(gameService.findAll(), deck);
            doStart++;
        }

        game.repartirCartes();
        sendDealingCardsToAPI();

        model.addAttribute("torn", game.getTorn());
        model.addAttribute("users", userList);
        model.addAttribute("repartir", game.getRepartir());
        game.nextRound();
        System.out.println(game.getRepartir());

        return "game";
    }

    private void sendDealingCardsToAPI() {
        for(int j=0; j<game.getJugadors().size(); j++) {
            gameService.addCardToUser(
                    game.getJugadors().get(j),
                    game.getJugadors().get(j).getCartes());

        }
    }


}
