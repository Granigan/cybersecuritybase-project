package sec.project.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UltimateEncoder implements PasswordEncoder{

    @Override
    public String encode(CharSequence cs) {
        return (String) cs;
    }

    @Override
    public boolean matches(CharSequence cs, String string) {
        return cs.equals(string);
    }
    
}
