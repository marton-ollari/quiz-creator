package quizcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizcreator.model.Question;
import quizcreator.model.QuestionGroup;
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
    
}
