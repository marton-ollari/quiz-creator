package quizcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizcreator.model.Question;
import quizcreator.model.QuestionType;
import quizcreator.model.Quiz;
import quizcreator.repository.QuestionRepository;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void saveQuestion(Question question){
        questionRepository.save(question);
    }

    public void saveQuestion(String question, String answer, QuestionType type, int lineNumber, Quiz quiz){
        questionRepository.save(new Question(question, answer, type, lineNumber, quiz));
    }

    public Question getQuestion(Quiz quiz, int lineNumber){
        return questionRepository.findByLineNumberAndQuiz(lineNumber, quiz);
    }
}
