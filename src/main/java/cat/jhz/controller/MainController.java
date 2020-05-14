package cat.jhz.controller;

import cat.jhz.model.User;
import cat.jhz.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(MainController.ROOT)
public class MainController {
    public static final String ROOT = "/";

    @Autowired
    GameService gameService;

    @Autowired
    HttpServletRequest request;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("pass",new User());
        return "index";
    }

    // Problema: cal identificar l'usuari que està en el joc per saber
    // i per validar la jugada que pugui fer, saber que la jugada l'ha fet un usuari determinat.
    // Amb httpServletRequest agafo la ip de qui fa la jugada, amb això puc verificar l'usuari
    // Caldria llegir le Header per poder tenir més usuaris des de la mariexa IP
    
    @PostMapping
    public WebAsyncTask checkPassword(@ModelAttribute User user, Model model) {
        model.addAttribute("users", gameService.findAll());
        WebAsyncTask task = new WebAsyncTask(8000,
                () -> {
                    System.out.println("init asynctask");
                   if(user.getPassword().equals("xavals")) {
                       user.setId(request.getRemoteAddr());
                       gameService.addUser(user);
                       return "redirect:game";
                   }else {
                        model.addAttribute("pass", new User());
                        return "index";
                    }
                }

                );



      /*  if(user.getPassword().equals("xavals")) {
            user.setId(request.getRemoteAddr());
            gameService.addUser(user);
            return "redirect:game";
        }
        else {
            model.addAttribute("pass", new User());
            return "index";
        }*/
        return task;
    }



}
