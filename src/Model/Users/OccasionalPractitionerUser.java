package Model.Users;

public class OccasionalPractitionerUser extends BaseUser {
    public OccasionalPractitionerUser(String name, String address, String email, int averageHeartRate) {
        super(name, address, email, averageHeartRate);
    }

    @Override
    public double getCalorieMultiplicationFactor() {
        final double heartRateConversionFactor = 0.05;
        return averageHeartRate * heartRateConversionFactor;
    }
}
