package com.shurapili.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("text", "Home Page");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("text", "About Page");
        return "about";
    }
}
