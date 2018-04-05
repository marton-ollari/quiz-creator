package quizcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizcreator.model.Quiz;
import quizcreator.model.User;
import quizcreator.repository.QuizRepository;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public void saveQuiz(Quiz quiz){
        quizRepository.save(quiz);
    }

    public void saveQuiz(String quizName, User user){
        quizRepository.save(new Quiz(quizName, user));
    }

    public Quiz getQuizById(long id){
        return quizRepository.findOne(id);
    }

    public Quiz getQuizByName(String name){
        return quizRepository.findByName(name);
    }


}
