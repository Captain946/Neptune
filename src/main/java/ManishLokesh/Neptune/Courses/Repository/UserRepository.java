package ManishLokesh.Neptune.Courses.Repository;

import ManishLokesh.Neptune.Courses.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

}
