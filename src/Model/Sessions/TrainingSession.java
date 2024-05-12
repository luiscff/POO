package Model.Sessions;

import Model.Activities.BaseActivity;
import Model.Users.BaseUser;

import java.io.Serializable;
import java.util.ArrayList;

public class TrainingSession implements Serializable {
    private final ArrayList<BaseActivity> activityList;

    public TrainingSession() {
        this.activityList = new ArrayList<>();
    }

    public TrainingSession(ArrayList<BaseActivity> activityList) {
        this.activityList = activityList;
    }

    public ArrayList<BaseActivity> getActivities() {
        return activityList;
    }

    public void addActivity(BaseActivity activity) {
        activityList.add(activity);
    }

    public int getCaloriesBurned(BaseUser user) {
        int totalCaloriesBurned = 0;
        for (BaseActivity activity : activityList) {
            totalCaloriesBurned += activity.getCaloriesBurned(user);
        }

        return totalCaloriesBurned;
    }
}
