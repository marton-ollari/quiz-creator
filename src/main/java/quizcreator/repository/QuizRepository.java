package quizcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizcreator.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
