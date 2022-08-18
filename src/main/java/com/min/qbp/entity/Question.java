package com.min.qbp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long id;

    private String subject;
    @Lob
    private String content;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();
}
