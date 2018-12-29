package sec.project.controller;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private EntityManager entityManager;

    @RequestMapping("*")
    public String defaultMapping(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "default";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String loadRegister() {
        return "register";
    }

    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@RequestParam String username, @RequestParam String password, @RequestParam String month) throws SQLException {
        Query query = entityManager.createNativeQuery(
                "INSERT INTO User (username, password, month) VALUES ('"
                + username + "', '" + password + "', '" + month + "');");

        query.executeUpdate();

        return "redirect:/default";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLogin() {
        return "login";
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public String loadPost(Authentication authentication) {
        if(authentication == null) {
            return "redirect:/default";
        }
        return "post";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String doPost(Authentication authentication, @RequestParam String message) {
        postRepo.save(new Post(authentication.getName(), message));
        return "redirect:/default";
    }
    
    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String loadReset() {
        return "reset";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String doReset(@RequestParam String username, @RequestParam String month) {
        if(userRepo.findByUsername(username).getMonth().toLowerCase().equals(month.toLowerCase())) {
            return "redirect:/edit/" + username;
        }
        return "redirect:/reset";
    }
    
    @RequestMapping(value = "/edit/{username}", method = RequestMethod.GET)
    public String loadEdit(Model model, @PathVariable String username) {
        model.addAttribute("user", userRepo.findByUsername(username));
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String doEdit(Model model, @RequestParam String username, @RequestParam String password) {
        User user = userRepo.findByUsername(username);
        System.out.println("old: " + user.getUsername() + ", " + user.getPassword());
        user.setPassword(password);
        System.out.println("updated: " + user.getUsername() + ", " + user.getPassword());
        userRepo.save(user);
        return "redirect:/login";
    }
}
