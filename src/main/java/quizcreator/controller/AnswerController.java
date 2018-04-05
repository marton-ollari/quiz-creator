package quizcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import quizcreator.service.AnswerService;

@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;
}
