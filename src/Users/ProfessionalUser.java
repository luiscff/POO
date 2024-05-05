package Users;

public class ProfessionalUser extends BaseUser {
    public ProfessionalUser(int id, String name, String address, String email, int averageHeartRate) {
        super(id, name, address, email, averageHeartRate);
    }

    @Override
    public double getCalorieMultiplicationFactor() {
        final double heartRateConversionFactor = 0.08;
        return averageHeartRate * heartRateConversionFactor;
    }
}
