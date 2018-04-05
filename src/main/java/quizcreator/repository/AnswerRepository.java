package quizcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizcreator.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
