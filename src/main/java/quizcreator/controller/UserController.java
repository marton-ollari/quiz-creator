package quizcreator.controller;

import com.google.gson.Gson;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quizcreator.model.User;
import quizcreator.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login-user", method = RequestMethod.POST)
    public String login(HttpServletRequest req, HttpSession session, Model model) {
        User user = userService.getUserByName(req.getParameter("name"));
        if(user != null && userService.checkPassword(req.getParameter("password"), user.getPassword())){
            session.setAttribute("name", req.getParameter("name"));
            session.setAttribute("id", user.getId());
            return "redirect:/";
        }else{
            model.addAttribute("error", "Login Failed! Username or Password invalid!");
            return "/index";
        }
    }

    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    public String register(Model model, HttpServletRequest req, HttpSession session){
        if(userService.checkIsUserNameFree(req.getParameter("name"))){
            userService.saveUser(req.getParameter("name"), req.getParameter("password"), req.getParameter("email"));
            User user = userService.getUserByName(req.getParameter("name"));
            session.setAttribute("name", req.getParameter("name"));
            session.setAttribute("id", user.getId());
            return "redirect:/";
        }else{
            model.addAttribute("error", "Registration Failed!");
            return "/index";
        }
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value="/islogged", method = RequestMethod.GET)
    public String isLogged(HttpSession session){
        Gson gson = new Gson();
        if(session.getAttribute("name") != null){
            return gson.toJson("true");
        } else {
            return gson.toJson("false");
        }
    }
}
