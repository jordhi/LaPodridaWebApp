package cat.jhz.service;

import cat.jhz.model.Card;
import cat.jhz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
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

    public void addCardToUser(User user, Card card) {
        //restTemplate.put(resource + "/" + user.getId() + "/cards/" + card.getId(),String.class);
        Map<String,String> params = new HashMap<>();
        params.put("id", user.getId());
        restTemplate.put(resource + "/" + user.getId(),card,Card.class);
    }

    public Image getImagefromDeck(String id) {
        return restTemplate.getForObject(resource_deck + id,Image.class);
    }


}
