package com.min.qbp.service;

import com.min.qbp.entity.Answer;
import com.min.qbp.entity.Question;
import com.min.qbp.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer save(String content, Question question) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setCreatedAt(LocalDateTime.now());

        return answerRepository.save(answer);
    }
}
