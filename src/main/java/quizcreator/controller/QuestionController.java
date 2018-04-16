package quizcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quizcreator.service.QuestionService;

import javax.servlet.http.HttpSession;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;


    @RequestMapping(value = "/save-question", method = RequestMethod.POST)
    public void saveQuestion(HttpSession session, Model model){
        int userId = (int)session.getAttribute("id");
    }



}
