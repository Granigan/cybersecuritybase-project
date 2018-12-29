package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sec.project.domain.User;

public interface PostRepository extends JpaRepository<User, Long> {

}
