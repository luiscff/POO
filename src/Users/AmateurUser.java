package Users;

public class AmateurUser extends BaseUser {
    public AmateurUser(int id, String name, String address, String email, int averageHeartRate) {
        super(id, name, address, email, averageHeartRate);
    }

    @Override
    public double getCalorieMultiplicationFactor() {
        final double heartRateConversionFactor = 0.07;
        return averageHeartRate * heartRateConversionFactor;
    }
}
