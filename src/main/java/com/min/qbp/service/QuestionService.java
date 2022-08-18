package com.min.qbp.service;

import com.min.qbp.entity.Question;
import com.min.qbp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    public Question save(Question question) {
        return repository.save(question);
    }

    public List<Question> findAll() {
        return repository.findAll();
    }
}
