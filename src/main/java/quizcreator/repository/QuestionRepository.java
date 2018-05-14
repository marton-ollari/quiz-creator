package quizcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizcreator.model.Question;
import quizcreator.model.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
