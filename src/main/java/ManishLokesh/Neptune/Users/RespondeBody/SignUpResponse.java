package ManishLokesh.Neptune.Users.RespondeBody;

import ManishLokesh.Neptune.Users.Entity.Signup;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class SignUpResponse {
    private String status;
    private String message;
    private String result;
    private Long id;
    private String mobileNumber;
    private String emailId;
    private String gender;
    private String fullName;
    private String createdAt;
    private String updatedAt;
    private String  otp;

    public SignUpResponse(){
        super();
    }

    public SignUpResponse(String status,String message, String result){
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public SignUpResponse(String status, String message,Long id, String fullName, String gender,
                          String createdAt, String updatedAt, String emailId, String mobileNumber,
                          String otp){
        this.status = status;
        this.message = message;
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.otp = otp;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
