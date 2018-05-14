package quizcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizcreator.model.Answer;
import quizcreator.model.Question;
import quizcreator.model.Quiz;
import quizcreator.model.User;
import quizcreator.repository.AnswerRepository;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }

    public void saveAnswer(User user, Question question, String answer){
        answerRepository.save(new Answer(user, question, answer));
    }

}
