package com.example.subscription.controller;

import com.example.subscription.model.News;
import com.example.subscription.model.NewsAdapter;
import com.example.subscription.model.User;
import com.example.subscription.repos.NewsRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsAdapter newsAdapter;

    private  NewsRepo newsRepo;

    public NewsController(NewsRepo messageRepo) {
        this.newsRepo = messageRepo;
    }

    @GetMapping
    public String list() {

        Gson gson = new GsonBuilder().registerTypeAdapter(News.class, newsAdapter).create();
        String jsonString =  gson.toJson(newsRepo.findAll());

        return jsonString;

    }

    @GetMapping("{id}")
    public News getOne(@PathVariable("id") News message) {

        Gson gson = new GsonBuilder().registerTypeAdapter(News.class, newsAdapter).create();
        String jsonString =  gson.toJson(message);

        return message;
    }

    @PostMapping
    public News create(@RequestBody News message, @AuthenticationPrincipal User user) {
        message.setAuthor(user);
        return newsRepo.save(message);
    }

    @PutMapping("{id}")
    public News update(
            @PathVariable("id") News messageFromDb,
            @RequestBody News message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return newsRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") News message) {
        newsRepo.delete(message);
    }
}
