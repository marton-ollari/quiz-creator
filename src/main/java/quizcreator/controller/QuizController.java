package quizcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import quizcreator.model.Quiz;
import quizcreator.model.User;
import quizcreator.service.QuizService;
import quizcreator.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(HttpSession session, Model model){
        if (session.getAttribute("id")!= null){
            model.addAttribute("userName", session.getAttribute("name"));
        }
        return "index";
    }

    @ResponseBody
    @PostMapping(value = "/save-quiz")
    public void saveQuiz(HttpSession session, HttpServletRequest req){
        User user = userService.getUserById((Long) session.getAttribute("id"));
        Quiz quiz = new Quiz(req.getParameter("quizname"), user);
        quizService.saveQuiz(quiz);
        session.setAttribute("quizId", quiz.getId());
    }
}
