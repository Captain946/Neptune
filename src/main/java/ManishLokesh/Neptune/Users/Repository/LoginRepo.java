package ManishLokesh.Neptune.Users.Repository;

import ManishLokesh.Neptune.Users.Entity.Login;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Login, Long> {
    @Bean
    Login findByMobileNumber(String mobileNumber);
}
