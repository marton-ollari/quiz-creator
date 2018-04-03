package quizcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizcreator.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
