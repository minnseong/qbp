package com.min.qbp.service;

import com.min.qbp.entity.Answer;
import com.min.qbp.entity.Question;
import com.min.qbp.entity.User;
import com.min.qbp.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer save(String content, Question question, User author) {
        Answer answer = Answer.builder()
                .content(content)
                .question(question)
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();

        return answerRepository.save(answer);
    }
}
