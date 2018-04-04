package quizcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quizcreator.service.QuizService;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String mainPage(){
        return "index";
    }
}
