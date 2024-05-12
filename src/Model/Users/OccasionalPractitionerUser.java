package Model.Users;

import java.time.LocalDate;

public class OccasionalPractitionerUser extends BaseUser {
    public OccasionalPractitionerUser(String name, String address, String email, LocalDate date) {
        super(name, address, email, date);
    }

    public OccasionalPractitionerUser(OccasionalPractitionerUser user) {//construtor de copia
        super(user);
    }

    @Override
    public double getCalorieMultiplicationFactor() {
        final double heartRateConversionFactor = 0.05;
        return averageHeartRate * heartRateConversionFactor;
    }

    @Override
    public OccasionalPractitionerUser clone() {
        return new OccasionalPractitionerUser(this);
    }
}
