package quizcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import quizcreator.model.Question;
import quizcreator.model.QuestionType;
import quizcreator.model.Quiz;
import quizcreator.service.QuestionService;
import quizcreator.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @ResponseBody
    @RequestMapping(value = "/save-question", method = RequestMethod.POST)
    public void saveQuestion(HttpSession session, HttpServletRequest req){
        QuestionType questionType = QuestionType.valueOf(req.getParameter("type").toUpperCase());
        Quiz quiz = quizService.getQuizById((long)session.getAttribute("quizId"));
        //TODO for loop to multiple questions
        //TODO line number is missing
        String question = req.getParameter("question1");
        String answer = req.getParameter("answer1");
        questionService.saveQuestion(new Question(question, answer, questionType, 1, quiz));

    }



}
