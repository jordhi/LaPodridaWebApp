package cat.jhz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(MainController.ROOT)
public class MainController {
    public static final String ROOT = "/";

}
