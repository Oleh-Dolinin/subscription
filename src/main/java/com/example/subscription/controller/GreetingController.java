package com.example.subscription.controller;

import com.example.subscription.model.User;
import com.example.subscription.repos.NewsRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GreetingController {

    @Value("${spring.profiles.active}")
    private String profile;

    private NewsRepo newsRepo;

    public GreetingController(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){
        Map<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("messages", newsRepo.findAll());

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
