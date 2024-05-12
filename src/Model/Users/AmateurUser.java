package Model.Users;

import java.time.LocalDate;

public class AmateurUser extends BaseUser {
    public AmateurUser(String name, String address, String email, LocalDate date) {
        super(name, address, email, date);
    }

    public AmateurUser(AmateurUser user) {//construtor de copia
        super(user);
    }


    @Override
    public double getCalorieMultiplicationFactor() {
        final double heartRateConversionFactor = 0.07;
        return averageHeartRate * heartRateConversionFactor;
    }

    @Override
    public AmateurUser clone() {
        return new AmateurUser(this);
    }
}
