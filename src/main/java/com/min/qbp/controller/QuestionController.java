package com.min.qbp.controller;

import com.min.qbp.entity.Question;
import com.min.qbp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qbp")
public class QuestionController {

    private final QuestionService service;

    @GetMapping("/question")
    public String showQuestions(Model model) {
        List<Question> questions = service.findAll();
        model.addAttribute("questions", questions);
        return "question_list";
    }
}
