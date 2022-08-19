package com.min.qbp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long id;

    private String title;
    @Lob
    private String content;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();
}
