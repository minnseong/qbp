package com.min.qbp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @Lob
    private String content;
    private LocalDateTime createdAt;
}