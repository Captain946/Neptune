package ManishLokesh.Neptune.Users.RespondeBody;

import ManishLokesh.Neptune.Users.Repository.LoginRepo;

public class OtpValidateResponse {
    private Long id;
    private String createdAt;
    private String fullName;
    private String emailId;
    private String mobileNumber;
    private String gender;
    private String updatedAt;

    public OtpValidateResponse(Long id, String createdAt,
                               String fullName, String emailId, String mobileNumber, String gender,
                               String updatedAt){
        this.id = id;
        this.createdAt = createdAt;
        this.fullName = fullName;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.updatedAt = updatedAt;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
