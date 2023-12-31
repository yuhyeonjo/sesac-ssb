package com.example.sbb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    // Answer N : 1 Question
    @ManyToOne
    private Question question;

    // Answer N : 1 SiteUser
    @ManyToOne
    private SiteUser author;

    // Answer N : N voter
    @ManyToMany
    Set<SiteUser> voter;
}
