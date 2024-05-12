package Controller;

import Model.Model;
import View.View;

import java.time.LocalDate;
import java.util.Arrays;

public class ControllerActivity {
    Model model;
    View view;

    String[] supportedActivities;
    String[] activitiesAltimetria;
    String[] activitiesDistance;
    String[] activitiesRepetitions;
    String[] activitiesWeight;



    public ControllerActivity(Model model, View view) {
        this.model = model;
        this.view = view;

        this.supportedActivities = model.supportedActivities;
        this.activitiesAltimetria = model.activitiesAltimetria;
        this.activitiesDistance = model.activitiesDistance;
        this.activitiesRepetitions = model.activitiesRepetitions;
        this.activitiesWeight = model.activitiesWeight;
    }


    public void registarActivity() {
        view.printMenu(model.supportedActivities, "Escolha uma atividade: ");
        int activity = view.readMenuOption(supportedActivities.length);


        int duracao = view.readBiggerThanZero("Insira a duração da atividade (minutos): ");
        int heartRate = view.readBiggerThanZero("Insira a frequência cardíaca média durante a atividade (bpm): ");
        LocalDate date = view.readDate("Insira a data da atividade (dd-mm-yyyy): ");

        try {
            switch (activity) {
                case 0:
                    view.printMessage("Operação cancelada");
                case 1:
                    String activityName = supportedActivities[activity - 1];
                    double distance = -1;
                    double altitude = -1;
                    int repetitions = -1;
                    double weight = -1;
                    //if activityName is in one of the lists, ask for the respective value
                    if (Arrays.asList(activitiesDistance).contains(activityName)) {
                        distance = view.readBiggerThanZero("Insira a distância percorrida (km): ");
                    } else if (Arrays.asList(activitiesAltimetria).contains(activityName)) {
                        //assumimos que a altimetria é sempre positiva para simplificar
                        altitude = view.readBiggerThanZero("Insira a altimetria acumulada (m): ");
                    } else if (Arrays.asList(activitiesRepetitions).contains(activityName)) {
                        repetitions = view.readBiggerThanZero("Insira o número de repetições: ");
                    } else if (Arrays.asList(activitiesWeight).contains(activityName)) {
                        weight = view.readBiggerThanZero("Insira o peso levantado (kg): ");
                    }
                    model.registarActivity(activityName, duracao, heartRate, distance, altitude,
                            repetitions, weight, date);
                case 2:
                    String activityName2 = supportedActivities[activity - 1];
                    double distance2 = -1;
                    double altitude2 = -1;
                    int repetitions2 = -1;
                    double weight2 = -1;
                    //if activityName is in one of the lists, ask for the respective value
                    if (Arrays.asList(activitiesDistance).contains(activityName2)) {
                        distance2 = view.readBiggerThanZero("Insira a distância percorrida (km): ");
                    } else if (Arrays.asList(activitiesAltimetria).contains(activityName2)) {
                        //assumimos que a altimetria é sempre positiva para simplificar
                        altitude2 = view.readBiggerThanZero("Insira a altimetria acumulada (m): ");
                    } else if (Arrays.asList(activitiesRepetitions).contains(activityName2)) {
                        repetitions2 = view.readBiggerThanZero("Insira o número de repetições: ");
                    } else if (Arrays.asList(activitiesWeight).contains(activityName2)) {
                        weight2 = view.readBiggerThanZero("Insira o peso levantado (kg): ");
                    }
                    model.registarActivity(activityName2, duracao, heartRate, distance2, altitude2,
                            repetitions2, weight2, date);
            }
        } catch (IllegalArgumentException e) {
            view.printError(e, "Erro ao registar atividade");
        }
    }

}
