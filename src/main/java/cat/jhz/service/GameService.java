package cat.jhz.service;

import cat.jhz.model.User;
import io.netty.handler.codec.socks.SocksRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${resource.game}/users")
    private String resource;

    public List<User> findAll() {
        return Arrays.stream(restTemplate.getForObject(resource, User[].class)).collect(Collectors.toList());
    }



}
