package cat.jhz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private List<User> jugadors;
    private Deck deck;
    private int torn, repartir;

    public Game(List<User> jugadors, Deck deck) {
        this.jugadors = jugadors;
        torn = new Random().nextInt(jugadors.size());
        jugadors.get(torn);
        repartir = 1;
        deck.createDeck();
        this.deck = deck;
    }

    public User getTorn() {
        return jugadors.get(torn);
    }

    public User nextTorn() {
        torn++;
        torn = torn % jugadors.size();
        return jugadors.get(torn);
    }

    //TODO TEST del repartiment
    public void repartirCartes() {
        for(int j=0; j<jugadors.size(); j++) {
            List<Card> c = new ArrayList<>();
            for(int i=0; i<repartir; i++) {
                c.add(deck.getRandomCard());
            }
            jugadors.get(j).setCartes(c);
        }
        System.out.println(jugadors.get(0).getCartes().toString());
        System.out.println(jugadors.get(1).getCartes().toString());
        //System.out.println(jugadors.get(2).getCartes().toString());
        //el proper cop que es reparteix una carta més
        repartir++;
    }

    public int getRepartir() {
        return repartir;
    }

    public List<User> getJugadors() {
        return jugadors;
    }
}
