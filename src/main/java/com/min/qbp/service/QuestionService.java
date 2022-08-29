package com.min.qbp.service;

import com.min.qbp.entity.Question;
import com.min.qbp.entity.User;
import com.min.qbp.exception.DataNotFoundException;
import com.min.qbp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository repository;

    public Question save(String title, String content, User author) {
        Question question = Question.builder()
                .title(title)
                .content(content)
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(question);
    }

    @Transactional
    public void edit(Long id, String title, String content) {
        Question question = repository.findById(id).get();

        question.setTitle(title);
        question.setContent(content);
        question.setModifiedAt(LocalDateTime.now());
    }

    public void delete(Long id) {
        repository.deleteById(id);
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

    public Page<Question> findAllByPage(int page) {
        Pageable pageable = PageRequest.of(page-1, 10);
        return repository.findAll(pageable);
    }
}
