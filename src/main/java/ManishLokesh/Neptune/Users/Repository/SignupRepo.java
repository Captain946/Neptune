package ManishLokesh.Neptune.Users.Repository;

import ManishLokesh.Neptune.Users.Entity.Signup;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepo extends JpaRepository<Signup,Long> {

    Signup findByEmailId(String emailId);
    Signup findByMobileNumber(String mobileNumber);

    Signup findByOtp(String otp);


}
