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
        QuestionType questionType = QuestionType.valueOf(req.getParameter("type"));
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
    @RequestMapping(value = "/update-question", method = RequestMethod.POST)
    public void updateQuestion(HttpSession session, HttpServletRequest req) {
        Quiz quiz = quizService.getQuizById((long) session.getAttribute("quizId"));
        QuestionGroup questionGroup = questionGroupService.getGroupByQuizAndLineNumber(quiz, Integer.parseInt(req.getParameter("groupNumber")));
        List<Question> questions = questionGroup.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (!questions.get(i).getQuestion().equals(req.getParameter("question" + (i+1)))) {
                question.setQuestion(req.getParameter("question" + (i+1)));
            }
            if (!questions.get(i).getAnswer().equals(req.getParameter("answer" + (i+1)))) {
                question.setAnswer(req.getParameter("answer" + (i+1)));
            }
            questionService.saveQuestion(question);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public String getSavedQuestions(@PathVariable int id, HttpSession session){
        Gson gson = new Gson();
        Quiz quiz = quizService.getQuizById((long)session.getAttribute("quizId"));
        QuestionGroup questionGroup = questionGroupService.getGroupByQuizAndLineNumber(quiz, id);
        HashMap<String, String> questionData = new HashMap<>();
        questionData.put("type", String.valueOf(questionGroup.getType()));
        List<Question> questions = questionGroup.getQuestions();
        questionData.put("size", String.valueOf(questions.size()));
        for (int i=1; i <= questions.size(); i++){
            questionData.put("question"+i, questions.get(i-1).getQuestion());
            questionData.put("answer"+i, questions.get(i-1).getAnswer());
        }
        return gson.toJson(questionData);
    }

    @ResponseBody
    @RequestMapping(value = "/group/{id}/type", method = RequestMethod.GET)
    public String getQuestionGroupType(@PathVariable int id, HttpSession session){
        Gson gson = new Gson();
        Quiz quiz = quizService.getQuizById((long)session.getAttribute("quizId"));
        QuestionGroup questionGroup = questionGroupService.getGroupByQuizAndLineNumber(quiz, id);
        return gson.toJson(String.valueOf(questionGroup.getType()));
    }



}
