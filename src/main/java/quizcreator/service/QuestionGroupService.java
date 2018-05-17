package quizcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizcreator.model.Question;
import quizcreator.model.QuestionGroup;
import quizcreator.model.Quiz;
import quizcreator.repository.QuestionGroupRepository;

import java.util.List;

@Service
public class QuestionGroupService {

    @Autowired
    private QuestionGroupRepository questionGroupRepository;

    public void saveQuestionGroup(QuestionGroup questionGroup){
        questionGroupRepository.save(questionGroup);
    }

    public QuestionGroup getGroupByQuizAndLineNumber(Quiz quiz, int lineNumber){
        return questionGroupRepository.getByQuizAndLineNumber(quiz, lineNumber);
    }
}
