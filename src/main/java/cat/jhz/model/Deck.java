package cat.jhz.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private List<Card> deck = new ArrayList<>();
    private String name;

    public Deck() {}

    public List<Card> getDeck() {
        return deck;
    }

    public List<Card> createDeck() {
        for(int j=1; j<=4; j++) {
            for (int i = 1; i <= 12; i++) {
                String is = String.valueOf(i);
                String pal = String.valueOf(j);
                deck.add(new Card(is + pal, is, pal));
            }
        }
        return deck;
    }

    public void deleteCard(String id) {
        List<Card> lc = deck.stream().filter(c -> c.getId().equals(id)).collect(Collectors.toList());
        if(lc.size()!=1) throw new IllegalStateException();
        else deck.remove(lc.get(0));
    }
}
