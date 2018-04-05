package quizcreator.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizcreator.model.User;
import quizcreator.repository.UserRepository;
import sun.security.util.Password;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public void saveUser(String name, String password, String email) {
        User user = new User(name, hashPassword(password), email);
        userRepository.save(user);
    }

    public void editUsername(String userName, int id){
        User user = getUserById(id);
        user.setName(userName);
        userRepository.save(user);
    }

    public void editPassword(String psw, int id){
        User user = getUserById(id);
        user.setPassword(hashPassword(psw));
        userRepository.save(user);
    }


    public boolean checkIsUserNameFree(String name) {
        return (getUserByName(name)== null);
    }


    private String hashPassword(String password_plaintext) {
        int workload = 12;
        String salt = BCrypt.gensalt(workload);

        return BCrypt.hashpw(password_plaintext, salt);
    }

    public boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return password_verified;
    }
}
