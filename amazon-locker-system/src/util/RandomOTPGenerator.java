package util;

import java.util.Random;

public class RandomOTPGenerator
        implements OTPGenerator {

    private final Random random = new Random();

    @Override
    public String generateOTP() {

        return String.format("%06d",
                random.nextInt(1000000));
    }
}