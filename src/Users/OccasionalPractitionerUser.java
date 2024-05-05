package Users;

public class OccasionalPractitionerUser extends BaseUser {
    public OccasionalPractitionerUser(int id, String name, String address, String email, int averageHeartRate) {
        super(id, name, address, email, averageHeartRate);
    }

    @Override
    public double getCalorieMultiplicationFactor() {
        final double heartRateConversionFactor = 0.05;
        return averageHeartRate * heartRateConversionFactor;
    }
}
