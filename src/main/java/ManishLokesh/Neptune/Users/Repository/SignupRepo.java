package ManishLokesh.Neptune.Users.Repository;

import ManishLokesh.Neptune.Users.Entity.Signup;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SignupRepo extends JpaRepository<Signup,Long> {
    @Bean
    Signup findByEmailId(String emailId);
    @Bean
    Signup findByMobileNumber(String mobileNumber);
    @Bean
    Signup findByOtp(String otp);


}
