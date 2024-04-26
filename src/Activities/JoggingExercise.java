package Activities;

import Trakers.IDistanceTracker;
import Trakers.IHeightTracker;

public class JoggingExercise implements IDistanceTracker, IHeightTracker {
    private double distanceTraveled;
    private double heightClimbed;

    public JoggingExercise() {
        this.distanceTraveled = 0;
        this.heightClimbed = 0;
    }

    public double getDistanceTraveled() {
        return this.distanceTraveled;
    }

    public void addDistanceTraveled(double distanceTraveled) {
        this.distanceTraveled += distanceTraveled;
    }

    public double getHeightClimbed() {
        return this.heightClimbed;
    }

    public void addHeightClimbed(double heightClimbed) {
        this.heightClimbed = heightClimbed;
    }

    @Override
    public String toString() {
        return String.format("""
            Jogging Exercise:\s
               Distance traveled: %f
               Height climbed: %f
            """,
            this.distanceTraveled,
            this.heightClimbed
        );
    }
}
