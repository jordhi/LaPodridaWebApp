package cat.jhz.model;

import java.util.List;
import java.util.Random;

public class Game {
    private List<User> jugadors;
    private Deck deck;
    private int torn;

    public Game(List<User> jugadors) {
        this.jugadors = jugadors;
        torn = new Random().nextInt(jugadors.size());
        jugadors.get(torn);
    }

    public User getTorn() {
        return jugadors.get(torn);
    }
}
