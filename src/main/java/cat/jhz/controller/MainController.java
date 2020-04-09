package cat.jhz.controller;

import cat.jhz.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(MainController.ROOT)
public class MainController {
    public static final String ROOT = "/";

    @GetMapping
    public String showForm(User player) {
        return "index";
    }

    @PostMapping
    public String checkPassword(@Valid User player, BindingResult result) {
        if(result.hasErrors()) return "index";
        return "redirect:/game";
    }

}
