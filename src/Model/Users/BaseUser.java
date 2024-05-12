package Model.Users;

import Model.Activities.BaseActivity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseUser implements Serializable {
    private static int lastId = 0;
    protected final int id;
    protected final String name;
    protected final String address;
    protected final String email;
    protected int averageHeartRate;
    private int totalHeartRate = 0; //usado para calcular a média
    private LocalDate dataDoSistema;

    protected Map<Integer, BaseActivity> pastActivities; // atividades já realizadas
    protected Map<Integer, BaseActivity> futureActivities; // atividades agendadas

    protected BaseUser(String name, String address, String email, LocalDate dataDoSistema) {
        this.id = ++lastId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.pastActivities = new HashMap<>();
        this.futureActivities = new HashMap<>();
        this.dataDoSistema = dataDoSistema;
    }

    protected BaseUser(BaseUser user) {//construtor de copia
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.pastActivities = user.getPastActivities(); // garante encapsulamento
        this.futureActivities = user.getFutureActivities(); // garante encapsulamento
        this.averageHeartRate = user.getAverageHeartRate();
        this.totalHeartRate = user.totalHeartRate;
        this.dataDoSistema = user.getDataDoSistema();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getAverageHeartRate() {
        return averageHeartRate;
    }

    public LocalDate getDataDoSistema() {
        return dataDoSistema;
    }


    protected Map<Integer, BaseActivity> getPastActivities() { // garante encapsulamento
        Map<Integer, BaseActivity> copy = new HashMap<>();
        for (Map.Entry<Integer, BaseActivity> entry : pastActivities.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }

    protected Map<Integer, BaseActivity> getFutureActivities() { // garante encapsulamento
        Map<Integer, BaseActivity> copy = new HashMap<>();
        for (Map.Entry<Integer, BaseActivity> entry : futureActivities.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }

    public void addActivity(BaseActivity activity) throws IllegalArgumentException {
        if (activity == null) {
            throw new IllegalArgumentException("Atividade inválida");

        } else if (activity.getDate().isBefore(this.dataDoSistema)) { //se a data da atividade for passado
            pastActivities.put(activity.getActivityId(), activity);
            this.updateAverageHeartRate(activity.getHeartRate());
        } else {
            //se a data da atividade for depois da data do sistema (ou no mesmo dia)
            futureActivities.put(activity.getActivityId(), activity);
        }
    }

    public void removePastActivity(int activityId) {
        pastActivities.remove(activityId);
    }

    public void removeFutureActivity(int activityId) {
        futureActivities.remove(activityId);
    }

    public void removeActivity(int activityId) {
        pastActivities.remove(activityId);
    }

    // devolve um array com as atividades do utilizador em formato de string
    public String[] getActivitiesList() {
        String[] array = new String[pastActivities.size() + futureActivities.size()];
        int i = 0;
        for (Map.Entry<Integer, BaseActivity> entry : pastActivities.entrySet()) {
            array[i++] = entry.getValue().toString();
        }
        for (Map.Entry<Integer, BaseActivity> entry : futureActivities.entrySet()) {
            array[i++] = entry.getValue().toString();
        }
        return array;
    }

    private void updateAverageHeartRate(int heartRate) {
        totalHeartRate += heartRate;
        averageHeartRate = totalHeartRate / pastActivities.size();
    }

    public void timeSkip(LocalDate newDate) {
        if (newDate.isAfter(dataDoSistema)) {
            // atualiza as atividades futuras
            List<Integer> keysToRemove = new ArrayList<>();
            for (Map.Entry<Integer, BaseActivity> entry : futureActivities.entrySet()) {
                if (entry.getValue().getDate().isBefore(newDate)) {
                    // se a data da atividade for antes da nova data, passa para as atividades passadas
                    pastActivities.put(entry.getKey(), entry.getValue());
                    this.updateAverageHeartRate(entry.getValue().getHeartRate());
                    //TODO ao adicionar estatísticas, é preciso atualizar-las no timeSkip

                    keysToRemove.add(entry.getKey()); // adiciona a chave para remover
                }
            }
            // remove as atividades passadas
            for (int key : keysToRemove) {
                futureActivities.remove(key);
            }

            // atualiza a data do sistema no utilizador
            dataDoSistema = newDate;
        } else {
            throw new IllegalArgumentException("Data de salto no tempo tem que ser no futuro");
        }
    }


    public abstract BaseUser clone();

    public abstract double getCalorieMultiplicationFactor();
}
