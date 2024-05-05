package Activities;

public abstract class BaseActivity {
    protected final ActivityDifficulty activityDifficulty;
    protected int durationInMinutes;

    protected BaseActivity() {
        this.durationInMinutes = 0;
        this.activityDifficulty = ActivityDifficulty.BASIC;
    }

    protected BaseActivity(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
        this.activityDifficulty = ActivityDifficulty.BASIC;
    }

    public BaseActivity(ActivityDifficulty activityDifficulty) {
        this.durationInMinutes = 0;
        this.activityDifficulty = activityDifficulty;
    }

    public BaseActivity(int durationInMinutes, ActivityDifficulty activityDifficulty) {
        this.durationInMinutes = durationInMinutes;
        this.activityDifficulty = activityDifficulty;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public ActivityDifficulty getActivityDifficulty() { return activityDifficulty; }

    public int getDurationInMinutes() { return durationInMinutes; }

    public abstract int getBurnedCalories();
}
