package sec.project.config;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sec.project.domain.User;
import sec.project.repository.PostRepository;
import sec.project.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;
    
    @Autowired
    PostRepository postRepo;
    
    @PostConstruct
    public void init() {
        User user = new User("username", "password", "january");
        userRepo.save(user);
        User def = new User("default", "default", "january");
        userRepo.save(def);
        User admin = new User("admin", "admin", "january");
        userRepo.save(admin);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = userRepo.findByUsername(username);
        if (currentUser == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                currentUser.getUsername(),
                currentUser.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
