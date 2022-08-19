package com.min.qbp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
