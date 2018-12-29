package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Post extends AbstractPersistable<Long> {

    private long userId;
    private String message;

    public Post() {
        super();
    }

    public Post(long userId, String message) {
        this();
        this.userId = userId;
        this.message = message;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
