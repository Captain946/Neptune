package ManishLokesh.Neptune.Users.Repository;

import ManishLokesh.Neptune.Users.Entity.Login;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepo extends JpaRepository<Login, Long> {
    Login findByMobileNumber(String mobileNumber);
}
