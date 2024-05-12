package Model.Activities;

import Model.Users.BaseUser;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class BaseActivity implements Serializable {
    private static int lastId = 0;
    private String activityName;
    private final int activityId;
    private final int userId;
    private int durationInMinutes;
    private final ActivityDifficulty activityDifficulty;
    private int heartRate;
    private LocalDate date;


    public BaseActivity(String activityName, int userId, int durationInMinutes, ActivityDifficulty activityDifficulty,
                        int heartRate, LocalDate date) {
        this.activityId = ++lastId;
        this.activityName = activityName;
        this.userId = userId;
        this.durationInMinutes = durationInMinutes;
        if (activityDifficulty == null) {
            this.activityDifficulty = ActivityDifficulty.BASIC;
        } else {
            this.activityDifficulty = activityDifficulty;
        }
        this.heartRate = heartRate;
        this.date = date;

    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public ActivityDifficulty getActivityDifficulty() { return activityDifficulty; }

    public int getDurationInMinutes() { return durationInMinutes; }

    public String getName() { return activityName; }

    public abstract int getCaloriesBurned(BaseUser user);

    public int getUserId() { return userId; }

    public int getActivityId() { return activityId; }

    public int getHeartRate() { return heartRate; }

    public LocalDate getDate() { return date; }

    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }

    //deve ser manter o activityId, senão vai ser uma atividade diferente
    public abstract BaseActivity clone();

    public abstract String toString();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseActivity baseActivity = (BaseActivity) obj;
        return (activityId == baseActivity.activityId &&
                userId == baseActivity.userId &&
                durationInMinutes == baseActivity.durationInMinutes &&
                activityDifficulty == baseActivity.activityDifficulty) &&
                heartRate == baseActivity.heartRate;
    }
}
