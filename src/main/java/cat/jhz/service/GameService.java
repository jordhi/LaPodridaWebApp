package cat.jhz.service;

import cat.jhz.model.Card;
import cat.jhz.model.Deck;
import cat.jhz.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${resource.game}/users")
    private String resource;

    @Value("${resource.game}/users/{id}")
    private String resource_newCard;

    @Value("${resource.game}/deck/")
    private String resource_deck;

    public List<User> findAll() {

        return Arrays.stream(
                Objects.requireNonNull(
                        restTemplate.getForObject(
                                resource, User[].class)
                )
        ).collect(Collectors.toList());
    }

    public void addUser(User user) {
        restTemplate.postForObject(resource, user, User.class);
    }

    public void addCardToUser(User user, List<Card> cards) {
        CardList cardList = new CardList();
        cardList.setCardList(cards);
        Map<String,String> params = new HashMap<>();
        params.put("id", user.getId());

        restTemplate.put(resource + "/" + user.getId() + "/cards",cards,CardList.class);
    }


    public Image getImagefromDeck(String id) {
        byte[] imagBytes = restTemplate.getForObject(resource_deck + id, byte[].class);
       return null;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class CardList {
    private List<Card> cardList;

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
