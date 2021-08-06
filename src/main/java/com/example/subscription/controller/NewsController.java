package com.example.subscription.controller;

import com.example.subscription.model.News;
import com.example.subscription.model.User;
import com.example.subscription.repos.NewsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("news")
public class NewsController {

    private  NewsRepo newsRepo;

    public NewsController(NewsRepo messageRepo) {
        this.newsRepo = messageRepo;
    }

    @GetMapping
    public List<News> list() {
        return newsRepo.findAll();
    }

    @GetMapping("{id}")
    public News getOne(@PathVariable("id") News message) {
        return message;
    }

    @PostMapping
    public News create(@RequestBody News message, @AuthenticationPrincipal User user) {
        message.setLocalDateTime(LocalDateTime.now());
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
