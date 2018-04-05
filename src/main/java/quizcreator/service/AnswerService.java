package quizcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quizcreator.repository.AnswerRepository;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
}
