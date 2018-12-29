package sec.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class User extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String username;
    private String password;
    private String answer;

    public User() {
        super();
    }

    public User(String name, String password, String answer) {
        this();
        this.username = name;
        this.password = password;
        this.answer = answer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

}
