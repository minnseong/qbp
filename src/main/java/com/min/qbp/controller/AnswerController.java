package com.min.qbp.controller;

import com.min.qbp.dto.form.AnswerForm;
import com.min.qbp.entity.Answer;
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/answer/edit/{id}")
    public String showEditForm(@PathVariable Long id, @ModelAttribute AnswerForm answerForm) {
        Answer answer = answerService.findById(id);

        answerForm.setContent(answer.getContent());
        return "answer_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/answer/edit/{id}")
    public String editAnswer(@PathVariable Long id, @Validated @ModelAttribute AnswerForm answerForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "answer_form";
        }
        Answer answer = answerService.findById(id);
        answerService.edit(id, answerForm.getContent());
        return "redirect:/qbp/question/" + answer.getQuestion().getId();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/answer/delete/{id}")
    public String deleteAnswer(@PathVariable Long id) {
        Answer answer = answerService.findById(id);
        answerService.delete(answer);
        return "redirect:/qbp/question/" + answer.getQuestion().getId();
    }
}
