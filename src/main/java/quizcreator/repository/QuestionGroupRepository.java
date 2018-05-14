package quizcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quizcreator.model.QuestionGroup;

public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, Long>{
}
