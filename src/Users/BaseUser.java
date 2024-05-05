package Users;

public abstract class BaseUser {
    protected final int id;
    protected final String name;
    protected final String address;
    protected final String email;
    protected final int averageHeartRate;

    protected BaseUser(int id, String name, String address, String email, int averageHeartRate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.averageHeartRate = averageHeartRate;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getEmail() { return email; }

    public int getAverageHeartRate() { return averageHeartRate; }

    public abstract double getCalorieMultiplicationFactor();
}
