package Model.Users;

import java.time.LocalDate;

public class ProfessionalUser extends BaseUser {
    public ProfessionalUser(String name, String address, String email, LocalDate date) {
        super(name, address, email, date);
    }

    public ProfessionalUser(ProfessionalUser user) {//construtor de copia
        super(user);
    }


    @Override
    public double getCalorieMultiplicationFactor() {
        final double heartRateConversionFactor = 0.08;
        return averageHeartRate * heartRateConversionFactor;
    }

    @Override
    public ProfessionalUser clone() {
        return new ProfessionalUser(this);
    }
}
