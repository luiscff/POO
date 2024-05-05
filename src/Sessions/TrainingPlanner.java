package Sessions;

public class TrainingPlanner {
    /**
     * Array with 7 boolean values, each one representing a day of the week.
     * Week starts on Monday.
     * If day is set to `true` it means a training session must be done on that day.
     * */
    private final boolean[] trainingDays;
    private TrainingSession trainingSession;

    public TrainingPlanner() {
        this.trainingDays = new boolean[] {false, false, false, false, false, false, false};
    }

    public boolean[] getRepetitionDays() {
        return trainingDays;
    }

    public TrainingSession getTrainingSession() {
        return trainingSession;
    }
}
