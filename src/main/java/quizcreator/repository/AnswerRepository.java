package quizcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizcreator.model.Answer;
import quizcreator.model.Quiz;
import quizcreator.model.User;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findByUserAndQuizAndLineNumber(User user, Quiz quiz, int lineNumber);
}
