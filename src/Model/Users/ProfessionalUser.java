package Model.Users;

public class ProfessionalUser extends BaseUser {
    public ProfessionalUser(String name, String address, String email, int averageHeartRate) {
        super(name, address, email, averageHeartRate);
    }

    @Override
    public double getCalorieMultiplicationFactor() {
        final double heartRateConversionFactor = 0.08;
        return averageHeartRate * heartRateConversionFactor;
    }
}
