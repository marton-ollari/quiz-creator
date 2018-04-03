package quizcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizcreator.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
