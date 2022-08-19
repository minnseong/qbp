package com.min.qbp.controller;

import com.min.qbp.dto.form.AnswerForm;
import com.min.qbp.entity.Question;
import com.min.qbp.entity.User;
import com.min.qbp.service.AnswerService;
import com.min.qbp.service.QuestionService;
import com.min.qbp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qbp")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/answer/{id}")
    public String saveAnswer(Model model, @Validated @ModelAttribute AnswerForm answerForm,
                             BindingResult bindingResult,
                             @PathVariable("id") Long QuestionId, Principal principal) {
        Question question = questionService.findById(QuestionId);
        User author = userService.getUserByUsername(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        answerService.save(answerForm.getContent(), question, author);
        return "redirect:/qbp/question/%s".formatted(QuestionId);
    }
}
