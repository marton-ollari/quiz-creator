package quizcreator.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import quizcreator.model.Question;
import quizcreator.model.QuestionGroup;
import quizcreator.model.QuestionType;
import quizcreator.model.Quiz;
import quizcreator.service.QuestionGroupService;
import quizcreator.service.QuestionService;
import quizcreator.service.QuizService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionGroupService questionGroupService;

    @ResponseBody
    @RequestMapping(value = "/save-question", method = RequestMethod.POST)
    public void saveQuestion(HttpSession session, HttpServletRequest req){
        QuestionType questionType = QuestionType.valueOf(req.getParameter("type").toUpperCase());
        Quiz quiz = quizService.getQuizById((long)session.getAttribute("quizId"));
        int questionNumber = Integer.parseInt(req.getParameter("number"));
        quiz.decreaseQuestionNumber(questionNumber);
        QuestionGroup questionGroup = new QuestionGroup(quiz, questionType, quiz.getQuestionGroupSize()+1);
        questionGroupService.saveQuestionGroup(questionGroup);
        for (int i=1; i <= questionNumber; i++){
            String question = req.getParameter("question" + i);
            String answer = req.getParameter("answer"+ i);
            questionService.saveQuestion(new Question(question, answer, i, questionGroup));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String getSavedQuestions(@PathVariable int id, HttpSession session){
        Gson gson = new Gson();
        Quiz quiz = quizService.getQuizById((long)session.getAttribute("quizId"));
        QuestionGroup questionGroup = questionGroupService.getQuestionsByGroup(quiz, id);
        HashMap<String, String> questionData = new HashMap<>();
        questionData.put("type", String.valueOf(questionGroup.getType()));
        List<Question> questions = questionGroup.getQuestions();
        for (int i=1; i <= questions.size(); i++){
            questionData.put("question"+i, questions.get(i).getQuestion());
            questionData.put("answer"+i, questions.get(i).getAnswer());
        }
        return gson.toJson(questionData);
    }



}
