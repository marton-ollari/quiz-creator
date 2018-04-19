package quizcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizcreator.model.Quiz;
import quizcreator.model.User;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz findByName(String name);
    Quiz findByNameAndUser(String name, User user);
}
