package Model.Users;

public class ProfessionalUser extends BaseUser {
    public ProfessionalUser(String name, String address, String email, int averageHeartRate) {
        super(name, address, email, averageHeartRate);
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
