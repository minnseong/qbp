package com.min.qbp.service;

import com.min.qbp.entity.Question;
import com.min.qbp.exception.DataNotFoundException;
import com.min.qbp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    public Question save(String title, String content) {
        Question question = Question.builder()
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(question);
    }

    public Question findById(Long id) {
        Optional<Question> findQuestion = repository.findById(id);

        if (findQuestion.isPresent()) {
            Question question = findQuestion.get();
            return question;
        }
        else {
            throw new DataNotFoundException("question not found");
        }
    }

    public List<Question> findAll() {
        return repository.findAll();
    }
}
