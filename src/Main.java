import Activities.JoggingExercise;

public class Main {
    public static void main(String[] args) {
        JoggingExercise joggingExercise = new JoggingExercise();

        joggingExercise.addDistanceTraveled(50);
        joggingExercise.addHeightClimbed(10);

        System.out.println(joggingExercise);
    }
}
