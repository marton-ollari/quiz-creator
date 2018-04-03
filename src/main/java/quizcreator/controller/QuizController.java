package quizcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import quizcreator.service.QuizService;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;
}
