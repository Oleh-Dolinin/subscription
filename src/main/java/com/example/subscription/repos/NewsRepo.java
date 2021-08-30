package com.example.subscription.repos;

import com.example.subscription.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface NewsRepo extends JpaRepository<News, Long> {

    List<News> findByTitleAndDescription (String title,String description);

    List<News> findByTitle (String title);

    List<News> findByDescription (String description);

}
