package com.min.qbp.controller;

import com.min.qbp.dto.form.AnswerForm;
import com.min.qbp.dto.form.QuestionForm;
import com.min.qbp.entity.Question;
import com.min.qbp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qbp")
public class QuestionController {

    private final QuestionService service;

    @GetMapping("/question/new")
    public String createForm(Model model) {
        model.addAttribute("questionForm", new QuestionForm());
        return "question_form";
    }

    @PostMapping("/question/new")
    public String createQuestion(@Validated @ModelAttribute QuestionForm questionForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        service.save(questionForm.getTitle(), questionForm.getContent());
        return "redirect:/qbp/question";
    }

    @GetMapping("/question")
    public String showQuestions(Model model) {
        List<Question> questions = service.findAll();
        model.addAttribute("questions", questions);
        return "question_list";
    }

    @GetMapping("/question/{id}")
    public String showQuestionDetail(@PathVariable Long id, Model model) {
        Question question = service.findById(id);
        model.addAttribute("question", question);
        model.addAttribute("answerForm", new AnswerForm());
        return "question_detail";
    }
}
