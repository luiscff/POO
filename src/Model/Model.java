package Model;

import Model.Activities.Corrida;
import Model.Users.AmateurUser;
import Model.Users.BaseUser;
import Model.Users.OccasionalPractitionerUser;
import Model.Users.ProfessionalUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private Map<Integer, BaseUser> users;
    private BaseUser userLoggedIn;

    //TODO: AVISO: CADA VEZ QUE SE ADICIONA UMA NOVA ATIVIDADE, TEM DE SE ADICIONAR A ESTAS LISTAS DE ACORDO COM OS IMPLEMENTS
    public final String[] supportedActivities = {"Corrida"};
    public final String[] activitiesAltimetria = {};
    public final String[] activitiesDistance = {"Corrida"};
    public final String[] activitiesRepetitions = {};
    public final String[] activitiesWeight = {};

    public Model() {
        this.users = new HashMap<>();
    }

    public String[] getActivitiesList() {
        return userLoggedIn.getActivitiesList();
    }

    public String getLoggedInUserName() {
        return userLoggedIn.getName();
    }

    public void registarUtilizador(String nome, String morada, String email, String nivel)
            throws IllegalArgumentException {
        switch (nivel) {
            case "Ocasional":
                BaseUser user = new OccasionalPractitionerUser(nome, morada, email);
                addUser(user);
                break;
            case "Amador":
                BaseUser user2 = new AmateurUser(nome, morada, email);
                addUser(user2);
                break;
            case "Profissional":
                BaseUser user3 = new ProfessionalUser(nome, morada, email);
                addUser(user3);
                break;
            default:
                throw new IllegalArgumentException("Nível de experiência inválido ao registar Utilizador: " + nivel);

        }
    }


    //TODO: AVISO: CADA VEZ QUE SE ADICIONA UMA NOVA ATIVIDADE, TEM DE SE ADICIONAR AO SWITCH CASE
    public void registarActivity(String activityName, int durationInMinutes,
                                 int heartRate, double distance,
                                 double altitude, int repetitions, double weight) {

        switch (activityName) {
            case "Corrida":
                Corrida activity = new Corrida(durationInMinutes, userLoggedIn.getId() , distance, heartRate);
                userLoggedIn.addActivity(activity);
                break;
            default:
                throw new IllegalArgumentException("Atividade inválida ao registar atividade: " + activityName);
        }
    }

    public void login(int id) {
        userLoggedIn = this.getUser(id);
    }

    public void addUser(BaseUser user) throws IllegalArgumentException {
        // Verifica se o ‘id’ do utilizador já existe no mapa
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user); // Adiciona o usuário ao mapa
        } else {
            // Se o ‘id’ já existir, lança uma exceção
            throw new IllegalArgumentException("ID de usuário já existe: " + user.getId());
        }
    }

    public BaseUser getUser(int id) {
        return users.get(id);
    }

    public void removeUser(int id) {
        users.remove(id);
    }

    public void updateUser(BaseUser user) {
        users.put(user.getId(), user);
    }

    // Retorna um map com o ‘id’ e o nome de cada utilizador
    public Map<Integer, String> getUserNames() {
        Map<Integer, String> usersMap = new HashMap<>();
        for (Map.Entry<Integer, BaseUser> entry : users.entrySet()) {
            usersMap.put(entry.getKey(), entry.getValue().getName());
        }
        return usersMap;
    }
}
