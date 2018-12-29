package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.domain.User;
import sec.project.repository.UserRepository;

@Controller
public class DefaultController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping("*")
    public String defaultMapping() {
        return "default";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLogin() {
        return "redirect:/default";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin() {
        return "redirect:/messages";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String messages() {
        return "redirect:/default";
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String post() {
        return "redirect:/default";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String username, @RequestParam String password, @RequestParam String answer) {
        userRepo.save(new User(username, password, answer));
        return "redirect:/messages";
    }

}
