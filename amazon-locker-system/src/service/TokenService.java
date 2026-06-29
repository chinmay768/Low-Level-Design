package service;

import enums.TokenStatus;
import exception.InvalidOTPException;
import exception.OTPExpiredException;
import model.AccessToken;
import model.Parcel;
import repository.AccessTokenRepository;
import util.OTPGenerator;

import java.time.Clock;
import java.time.LocalDateTime;

public class TokenService {

    private final OTPGenerator otpGenerator;

    private final Clock clock;

    private final AccessTokenRepository repository;

    public TokenService(
            OTPGenerator otpGenerator,
            Clock clock,
            AccessTokenRepository repository) {

        this.otpGenerator = otpGenerator;
        this.clock = clock;
        this.repository = repository;
    }

    public AccessToken createToken(
            Parcel parcel) {

        AccessToken token =
                new AccessToken(
                        otpGenerator.generateOTP(),
                        LocalDateTime.now(clock).plusDays(2)
                );

        repository.save(parcel.getId(),
                token);

        return token;
    }

    public void validateToken(String parcelId, String enteredOtp) {

        AccessToken token = getToken(parcelId);

        if (token.isExpired(clock)) {
            throw new OTPExpiredException("OTP has expired.");
        }

        if (!token.getOtp().equals(enteredOtp)) {
            throw new InvalidOTPException("Invalid OTP.");
        }

        if (token.getStatus() != TokenStatus.ACTIVE) {
            throw new InvalidOTPException("OTP is no longer active.");
        }
    }

    public void consumeToken(String parcelId) {

        AccessToken token = getToken(parcelId);

        token.markUsed();

        repository.delete(parcelId);
    }

    public AccessToken getToken(String parcelId) {

        AccessToken token = repository.findByParcelId(parcelId);

        if (token == null) {
            throw new InvalidOTPException("No OTP found for parcel: " + parcelId);
        }

        return token;
    }

}