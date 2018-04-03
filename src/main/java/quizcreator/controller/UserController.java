package quizcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import quizcreator.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
}
