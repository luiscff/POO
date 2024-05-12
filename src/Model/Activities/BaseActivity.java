package Model.Activities;

import Model.Users.BaseUser;

import java.io.Serializable;

public abstract class BaseActivity implements Serializable {
    private static int lastId = 0;
    private String activityName;
    private final int activityId;
    private final int userId;
    private int durationInMinutes;
    private final ActivityDifficulty activityDifficulty;

    protected BaseActivity(String activityName, int userId) {
        this.activityId = ++lastId;
        this.activityName = activityName;
        this.userId = userId;
        this.durationInMinutes = 0;
        this.activityDifficulty = ActivityDifficulty.BASIC;
    }

    protected BaseActivity(String activityName, int userId, int durationInMinutes) {
        this.activityId = ++lastId;
        this.activityName = activityName;
        this.userId = userId;
        this.durationInMinutes = durationInMinutes;
        this.activityDifficulty = ActivityDifficulty.BASIC;
    }

    public BaseActivity(String activityName, int userId, ActivityDifficulty activityDifficulty) {
        this.activityId = ++lastId;
        this.activityName = activityName;
        this.userId = userId;
        this.durationInMinutes = 0;
        this.activityDifficulty = activityDifficulty;
    }

    public BaseActivity(String activityName, int userId, int durationInMinutes, ActivityDifficulty activityDifficulty) {
        this.activityId = ++lastId;
        this.activityName = activityName;
        this.userId = userId;
        this.durationInMinutes = durationInMinutes;
        if (activityDifficulty == null) {
            this.activityDifficulty = ActivityDifficulty.BASIC;
        } else {
            this.activityDifficulty = activityDifficulty;
        }
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
                activityDifficulty == baseActivity.activityDifficulty);
    }
}
