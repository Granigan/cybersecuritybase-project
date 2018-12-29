package sec.project.controller;

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
    public String loadRegister() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@RequestParam String username, @RequestParam String password, @RequestParam String answer) {
        userRepo.save(new User(username, password, answer));
        return "redirect:/default";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam String username, @RequestParam String password) {
        if(userRepo.findByUsername(username).getPassword().equals(password)) {
            // luo auth
        }
        return "redirect:/default";
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String loadPost() {
        return "post";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String doPost(@RequestParam String message) {
        postRepo.save(new Post(10, message));
        return "redirect:/default";
    }

}
