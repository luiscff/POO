package Model.Activities;

import Model.Users.BaseUser;

public class Corrida extends BaseActivity implements Distance {

    private double distance; //km

    public Corrida(int durationInMinutes, int userId, double distance) {
        super("Corrida", userId, durationInMinutes, ActivityDifficulty.BASIC);
        this.distance = distance;
    }

    public Corrida(Corrida corrida) { //construtor de copia (mantém o mesmo activityId)
        super(corrida.getName(), corrida.getUserId(), corrida.getDurationInMinutes(), corrida.getActivityDifficulty());
        this.distance = corrida.getDistance();
    }

    //em kcal
    private double formulaCorrida(int minutes, double distance){
        double total = 0;
        if (minutes == 0) return total;
        double hours = minutes / 60.0;
        double speed = distance / hours; //km/h
        //1 kcal por hora vezes o peso corporal vezes a velocidade em km/h
        total = (1 * hours * 70 * speed); //assumindo que o peso corporal é 70kg para simplificar
        return total;
    }

    //em calorias
    @Override
    public int getCaloriesBurned(BaseUser user) {
        double kcal = formulaCorrida(this.getDurationInMinutes(),this.getDistance());
        double calories = kcal / 1000; //converte para calorias
        return (int) (calories * user.getCalorieMultiplicationFactor());
    }


    @Override
    public double getDistance() {
        return this.distance;
    }

    @Override
    public void setDistance(double distance) {
        this.distance = distance;
    }


    @Override
    public Corrida clone() {
        return new Corrida(this);
    }

    @Override
    public String toString() {
        return this.getName()+"{" +
                "activityId=" + getActivityId() +
                ", userId=" + getUserId() +
                ", durationInMinutes=" + getDurationInMinutes() +
                ", activityDifficulty=" + getActivityDifficulty() +
                ", distance=" + getDistance() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corrida)) return false;
        if (!super.equals(o)) return false;
        Corrida corrida = (Corrida) o;
        return Double.compare(corrida.getDistance(), getDistance()) == 0;
    }
}
