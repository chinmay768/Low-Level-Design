package model;

import enums.TokenStatus;

import java.time.LocalDateTime;

public class AccessToken {

    private final String otp;

    private final LocalDateTime expiryTime;

    private TokenStatus status;

    public AccessToken(String otp,
                       LocalDateTime expiryTime) {

        this.otp = otp;
        this.expiryTime = expiryTime;

        this.status = TokenStatus.ACTIVE;
    }

    public String getOtp() {
        return otp;
    }

    public boolean isExpired() {

        return LocalDateTime.now().isAfter(expiryTime);
    }

    public boolean isValid() {

        return status == TokenStatus.ACTIVE &&
                !isExpired();
    }

    public void markUsed() {

        status = TokenStatus.USED;
    }
}
