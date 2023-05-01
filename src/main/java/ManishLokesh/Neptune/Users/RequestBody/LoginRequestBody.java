package ManishLokesh.Neptune.Users.RequestBody;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginRequestBody {

    @NotNull(message = "mobile number not null")
    @NotBlank(message = "mobile number not blank")
    private String mobileNumber;

    @NotNull(message = "password not null")
    @NotBlank(message = "password not blank")
    private String password;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
