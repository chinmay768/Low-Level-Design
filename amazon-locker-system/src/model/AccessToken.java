package model;

import enums.TokenStatus;

import java.time.Clock;
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

    public boolean isExpired(Clock clock) {
        return LocalDateTime.now(clock).isAfter(expiryTime);
    }


    public boolean isValid(Clock clock) {
        return status == TokenStatus.ACTIVE &&
                !isExpired(clock);
    }

    public void markUsed() {

        status = TokenStatus.USED;
    }

    public TokenStatus getStatus() {
        return status;
    }
}
