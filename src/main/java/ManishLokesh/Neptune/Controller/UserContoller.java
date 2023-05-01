package ManishLokesh.Neptune.Controller;

import ManishLokesh.Neptune.Entity.User;
import ManishLokesh.Neptune.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserContoller {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/v2/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody User user){
        if(userRepository.findByEmail(user.getEmail()) != null){
            return new ResponseEntity<>(new UserResponse("failure","User already Exist"), HttpStatus.BAD_REQUEST);
        }
        User saveUser = userRepository.save(user);
        UserResponse response = new UserResponse("success","User created successfully",saveUser.getId(), saveUser.getFullName(), saveUser.getGender(), saveUser.getEmail(), saveUser.getMobileNumber());
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    public static class UserResponse {
        private String status;
        private String message;
        private Object result;
        private Long id;
        private String fullName;
        private String email;
        private String mobileNumber;
        private String gender;

        public UserResponse(String status,String message) {
            this.status = status;
            this.message = message;

        }

        public UserResponse(String status, String message,Long id, String fullName, String gender, String email, String mobileNumber){
            this.status = status;
            this.message = message;
            this.id = id;
            this.fullName = fullName;
            this.gender = gender;
            this.email = email;
            this.mobileNumber = mobileNumber;

        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }



}

