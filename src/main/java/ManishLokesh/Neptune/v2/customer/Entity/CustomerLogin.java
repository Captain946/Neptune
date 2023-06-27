package ManishLokesh.Neptune.v2.customer.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CustomerLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String createdAt;
    private String updatedAt;
    private String fullName;
    private String mobileNumber;
    private String emailId;
    private String gender;
    private String password;
    private String lastLogin;
    private String role;

}
