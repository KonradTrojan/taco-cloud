package trojan.taco.repository.jpa;

import trojan.taco.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
