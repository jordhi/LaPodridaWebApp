package cat.jhz.model;

import java.util.List;
import java.util.Random;

public class Game {
    private List<User> jugadors;
    private Deck deck;
    private int torn, repartir;

    public Game(List<User> jugadors) {
        this.jugadors = jugadors;
        torn = new Random().nextInt(jugadors.size());
        jugadors.get(torn);
        repartir = 1;
    }

    public User getTorn() {
        return jugadors.get(torn);
    }

    public User nextTorn() {
        torn++;
        torn = torn % jugadors.size();
        return jugadors.get(torn);
    }
}
