package ManishLokesh.Neptune.Users.RequestBody;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OtpValidateRequestBody {

    @NotBlank(message = "Not blank")
    @NotNull(message = "not null")
    private String mobileNumber;

    @NotBlank(message = "otp are not blank")
    @NotNull(message = "otp are not null")
    private String otp;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
