package com.min.qbp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/qbp")
    public String index() {
        return "hello";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/qbp/question";
    }
}
