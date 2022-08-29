package com.min.qbp.service;

import com.min.qbp.entity.Answer;
import com.min.qbp.entity.Question;
import com.min.qbp.entity.User;
import com.min.qbp.exception.DataNotFoundException;
import com.min.qbp.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


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

    public Answer findById(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);

        if (answer.isEmpty()) {
            throw new DataNotFoundException("해당 답변은 존재하지 않습니다.");
        }

        return answer.get();
    }

    @Transactional
    public Answer edit(Long id, String content) {
        Optional<Answer> findAnswer = answerRepository.findById(id);

        if (findAnswer.isEmpty()) {
            throw new DataNotFoundException("해당 답변은 존재하지 않습니다.");
        }

        Answer answer = findAnswer.get();
        answer.setContent(content);
        answer.setModifiedAt(LocalDateTime.now());

        return answer;
    }

    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }
}
