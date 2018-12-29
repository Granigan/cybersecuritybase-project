package sec.project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Post;
import sec.project.domain.User;
import sec.project.repository.PostRepository;
import sec.project.repository.UserRepository;

@Controller
public class DefaultController {

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private PostRepository postRepo;

    @RequestMapping("*")
    public String defaultMapping(Model model) {
        model.addAttribute("posts", postRepo.findAll());
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
