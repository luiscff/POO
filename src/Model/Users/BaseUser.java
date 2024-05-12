package Model.Users;

import Model.Activities.BaseActivity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseUser implements Serializable {
    private static int lastId = 0;
    protected final int id;
    protected final String name;
    protected final String address;
    protected final String email;
    protected int averageHeartRate;
    private int totalHeartRate = 0; //usado para calcular a média

    protected Map<Integer, BaseActivity> activities;

    protected BaseUser(String name, String address, String email) {
        this.id = ++lastId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.activities = new HashMap<>();
    }

    protected BaseUser(BaseUser user) {//construtor de copia
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.activities = user.getActivities(); // garante encapsulamento
        this.averageHeartRate = user.getAverageHeartRate();
        this.totalHeartRate = user.totalHeartRate;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getEmail() { return email; }

    public int getAverageHeartRate() { return averageHeartRate; }


    protected Map<Integer, BaseActivity> getActivities() { // garante encapsulamento
        Map<Integer, BaseActivity> copy = new HashMap<>();
        for (Map.Entry<Integer, BaseActivity> entry : activities.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }

    public void addActivity(BaseActivity activity) throws IllegalArgumentException {
        if (activity == null) {
            throw new IllegalArgumentException("Atividade inválida");
        } else if (activities.containsValue(activity)) {
            throw new IllegalArgumentException("Atividade já registada");
        } else {
            activities.put(activity.getActivityId(), activity);
            this.updateAverageHeartRate(activity.getHeartRate());
        }
    }

    public void removeActivity(int activityId) {
        activities.remove(activityId);
    }

    // devolve um array com as atividades do utilizador em formato de string
    public String[] getActivitiesList() {
        String[] list = new String[activities.size()];
        int i = 0;
        for (Map.Entry<Integer, BaseActivity> entry : activities.entrySet()) {
            list[i++] = entry.getValue().toString();
        }
        return list;
    }

    private void updateAverageHeartRate(int heartRate) {
        totalHeartRate += heartRate;
        averageHeartRate = totalHeartRate / activities.size();
    }


    public abstract BaseUser clone();

    public abstract double getCalorieMultiplicationFactor();
}
