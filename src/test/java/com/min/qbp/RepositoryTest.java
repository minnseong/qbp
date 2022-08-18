package com.min.qbp;

import com.min.qbp.entity.Question;
import com.min.qbp.repository.AnswerRepository;
import com.min.qbp.repository.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void saveQuestionTest() {
        Question question = new Question();
        question.setTitle("jpa 질문이 있습니다.");
        question.setContent("jpa에서 @Entity가 무슨 기능을 하는지 궁금합니다.");
        question.setCreatedAt(LocalDateTime.now());
        questionRepository.save(question);

        Question question2 = new Question();
        question2.setTitle("spring 질문이 있습니다.");
        question2.setContent("spring에서 인터셉터는 무슨 역할을 하나요?");
        question2.setCreatedAt(LocalDateTime.now());
        questionRepository.save(question2);

        assertThat(questionRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    void findByIdTest() {
        Question question = new Question();
        question.setTitle("질문 제목입니다.");
        question.setContent("질문 내용입니다.");
        question.setCreatedAt(LocalDateTime.now());
        questionRepository.save(question);

        Optional<Question> findQuestion = questionRepository.findById(question.getId());

        if (findQuestion.isPresent()) {
            Question findQ = findQuestion.get();
            assertThat(findQ.getTitle()).isEqualTo("질문 제목입니다.");
        }
    }
}
