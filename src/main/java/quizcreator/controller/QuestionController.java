package quizcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import quizcreator.service.QuestionService;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
}
