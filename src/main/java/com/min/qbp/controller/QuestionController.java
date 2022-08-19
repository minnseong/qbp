package com.min.qbp.controller;

import com.min.qbp.dto.form.AnswerForm;
import com.min.qbp.dto.form.QuestionForm;
import com.min.qbp.entity.Question;
import com.min.qbp.entity.User;
import com.min.qbp.service.QuestionService;
import com.min.qbp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/question/new")
    public String createForm(Model model) {
        model.addAttribute("questionForm", new QuestionForm());
        return "question_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/question/new")
    public String createQuestion(@Validated @ModelAttribute QuestionForm questionForm, BindingResult bindingResult, Principal principal) {

        User author = userService.getUserByUsername(principal.getName());

        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        questionService.save(questionForm.getTitle(), questionForm.getContent(), author);
        return "redirect:/qbp/question";
    }

    @GetMapping("/question")
    public String showQuestions(Model model, @RequestParam(value="page", defaultValue = "1") int page) {
//        List<Question> questions = service.findAll();
//        model.addAttribute("questions", questions);
        Page<Question> questions = questionService.findAllByPage(page);
        model.addAttribute("questions", questions);
        return "question_list";
    }

    @GetMapping("/question/{id}")
    public String showQuestionDetail(@PathVariable Long id, Model model) {
        Question question = questionService.findById(id);
        model.addAttribute("question", question);
        model.addAttribute("answerForm", new AnswerForm());
        return "question_detail";
    }
}
