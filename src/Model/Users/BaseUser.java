package Model.Users;

import Model.Activities.BaseActivity;

import java.io.Serializable;

public abstract class BaseUser implements Serializable {
    private static int lastId = 0;
    protected final int id;
    protected final String name;
    protected final String address;
    protected final String email;
    protected final int averageHeartRate;
    protected BaseActivity[] activities;

    protected BaseUser(String name, String address, String email, int averageHeartRate) {
        this.id = ++lastId;
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
