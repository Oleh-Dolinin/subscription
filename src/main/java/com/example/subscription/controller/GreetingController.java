package com.example.subscription.controller;

import com.example.subscription.model.User;
import com.example.subscription.repos.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class GreetingController {

    private final NewsRepo newsRepo;

    public GreetingController(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();


        data.put("messages",newsRepo.findAll());
        data.put("profile", user);

        model.addAttribute("frontendData", data);

        return "index";
    }
}
