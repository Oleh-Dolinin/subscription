package com.example.subscription.controller;

import com.example.subscription.model.News;
import com.example.subscription.model.NewsAdapter;
import com.example.subscription.model.User;
import com.example.subscription.model.UserAdapter;
import com.example.subscription.repos.NewsRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class GreetingController {

    @Autowired
    private NewsAdapter newsAdapter;

    @Autowired
    private UserAdapter userAdapter;

    private NewsRepo newsRepo;

    public GreetingController(NewsRepo newsRepo) {
        this.newsRepo = newsRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();

        Gson gson = new GsonBuilder().registerTypeAdapter(News.class, newsAdapter).create();
        String jsonString =  gson.toJson(newsRepo.findAll());
        data.put("messages", jsonString);

        Gson gsonU = new GsonBuilder().registerTypeAdapter(User.class, userAdapter).create();
        String jsonStringU =  gsonU.toJson(user);

        if (jsonStringU.equals("null")){
            data.put("profile", null);
        }
        else data.put("profile", jsonStringU);
        data.put("profiles", user);


        model.addAttribute("frontendData", data);

        return "index";
    }
}
