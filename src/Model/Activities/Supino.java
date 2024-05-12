package Model.Activities;

import Model.Users.BaseUser;

import java.time.LocalDate;

public class Supino extends BaseActivity implements Weight, Repetitions{
    private double weight;
    private int repetitions;

    public Supino(int durationInMinutes, int userId, int heartRate, double weight, int repetitions, LocalDate date) {
        super("Supino", userId, durationInMinutes, ActivityDifficulty.HARD, heartRate, date);
        this.weight = weight;
        this.repetitions = repetitions;
    }

    public Supino(Supino supino) { //construtor de copia (mantém o mesmo activityId)
        super(supino.getName(), supino.getUserId(), supino.getDurationInMinutes(),
                supino.getActivityDifficulty(), supino.getHeartRate(), supino.getDate());
        this.weight = supino.getWeight();
        this.repetitions = supino.getRepetitions();
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int getRepetitions() {
        return repetitions;
    }

    @Override
    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    //em kcal
    private double formulaSupino(int minutes){
        double total = 0;
        if (minutes == 0) return total;
        double hours = minutes / 60.0;
        //1 kcal por hora
        total = 1 * hours;
        return total;
    }



    //em calorias
    @Override
    public int getCaloriesBurned(BaseUser user) {
        double kcal = formulaSupino(this.getDurationInMinutes());
        double calories = kcal / 1000; //converte para calorias
        return (int) (calories * user.getCalorieMultiplicationFactor());
    }

    @Override
    public BaseActivity clone() {
        return new Supino(this);
    }

    @Override
    public String toString() {
        return this.getName()+"{" +
                "activityId=" + getActivityId() +
                ", userId=" + getUserId() +
                ", durationInMinutes=" + getDurationInMinutes() +
                ", activityDifficulty=" + getActivityDifficulty() +
                ", heartRate=" + getHeartRate() +
                ", weight=" + getWeight() +
                ", repetitions=" + getRepetitions() +
                ", date=" + getDate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supino)) return false;
        if (!super.equals(o)) return false;
        Supino supino = (Supino) o;
        return Double.compare(supino.getWeight(), getWeight()) == 0 &&
                getRepetitions() == supino.getRepetitions();
    }
}
