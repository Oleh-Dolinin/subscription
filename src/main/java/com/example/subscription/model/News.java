package com.example.subscription.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

}
