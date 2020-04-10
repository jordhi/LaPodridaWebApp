package cat.jhz.controller;

import cat.jhz.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(MainController.ROOT)
public class MainController {
    public static final String ROOT = "/";

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("pass",new User());
        return "index";
    }

    @PostMapping
    public String checkPassword(@ModelAttribute User user, Model model) {
        if(user.getPassword().equals("xavals")) return "game";
        else {
            model.addAttribute("pass", new User());
            return "index";
        }
    }

}
