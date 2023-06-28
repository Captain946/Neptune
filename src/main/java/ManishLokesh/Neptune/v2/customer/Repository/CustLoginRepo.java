package ManishLokesh.Neptune.v2.customer.Repository;

import ManishLokesh.Neptune.v2.customer.Entity.CustomerLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustLoginRepo extends JpaRepository<CustomerLogin,Long> {
    CustomerLogin findByMobileNumber(String mobileNumber);
}
