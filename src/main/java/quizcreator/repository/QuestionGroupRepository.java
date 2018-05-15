package quizcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizcreator.model.QuestionGroup;
import quizcreator.model.Quiz;

public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, Long>{

    QuestionGroup getByQuizAndLineNumber(Quiz quiz, Integer lineNumber);
}
