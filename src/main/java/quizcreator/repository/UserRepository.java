package quizcreator.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quizcreator.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByName(String name);
}
