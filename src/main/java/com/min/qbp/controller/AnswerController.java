package com.min.qbp.controller;

import com.min.qbp.entity.Answer;
import com.min.qbp.entity.Question;
import com.min.qbp.service.AnswerService;
import com.min.qbp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qbp")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/answer/{id}")
    public String saveAnswer(@RequestParam String content,
                           @PathVariable("id") Long QuestionId) {
        Question question = questionService.findById(QuestionId);
        answerService.save(content, question);

        return "redirect:/qbp/question/%s".formatted(QuestionId);
    }
}
