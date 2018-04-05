package quizcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quizcreator.service.QuizService;

import javax.servlet.http.HttpSession;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(HttpSession session, Model model){
        if (session.getAttribute("id")!= null){
            model.addAttribute("userName", session.getAttribute("name"));
        }
        return "index";
    }
}
