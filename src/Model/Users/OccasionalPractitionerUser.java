package Model.Users;

public class OccasionalPractitionerUser extends BaseUser {
    public OccasionalPractitionerUser(String name, String address, String email, int averageHeartRate) {
        super(name, address, email, averageHeartRate);
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
