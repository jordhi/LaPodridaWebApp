package cat.jhz.service;

import cat.jhz.model.Card;
import cat.jhz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    @Async
    public CompletableFuture<List<User>> findAll() {
        List<User> results = Arrays.stream(
                Objects.requireNonNull(
                        restTemplate.getForObject(
                                resource, User[].class)
                )
        ).collect(Collectors.toList());
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(results);
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
    //TODO comprovar i usar aquest nou metode per veure si respon al getmapping de la api
    public Image getImagefromDeck(String id) {
        byte[] imagBytes = restTemplate.getForObject(resource_deck + id, byte[].class);
       return null;
    }


}
