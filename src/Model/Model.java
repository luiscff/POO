package Model;

import Model.Activities.Corrida;
import Model.Users.AmateurUser;
import Model.Users.BaseUser;
import Model.Users.OccasionalPractitionerUser;
import Model.Users.ProfessionalUser;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private Map<Integer, BaseUser> users;
    private BaseUser userLoggedIn;
    protected LocalDate date;

    //TODO: AVISO: CADA VEZ QUE SE ADICIONA UMA NOVA ATIVIDADE, TEM DE SE ADICIONAR A ESTAS LISTAS DE ACORDO COM OS IMPLEMENTS
    public final String[] supportedActivities = {"Corrida"};
    public final String[] activitiesAltimetria = {};
    public final String[] activitiesDistance = {"Corrida"};
    public final String[] activitiesRepetitions = {};
    public final String[] activitiesWeight = {};

    public Model() {
        this.users = new HashMap<>();
        this.userLoggedIn = null;
        this.date = LocalDate.now();
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
                BaseUser user = new OccasionalPractitionerUser(nome, morada, email, date);
                addUser(user);
                break;
            case "Amador":
                BaseUser user2 = new AmateurUser(nome, morada, email, date);
                addUser(user2);
                break;
            case "Profissional":
                BaseUser user3 = new ProfessionalUser(nome, morada, email, date);
                addUser(user3);
                break;
            default:
                throw new IllegalArgumentException("Nível de experiência inválido ao registar Utilizador: " + nivel);

        }
    }


    //TODO: AVISO: CADA VEZ QUE SE ADICIONA UMA NOVA ATIVIDADE, TEM DE SE ADICIONAR AO SWITCH CASE
    public void registarActivity(String activityName, int durationInMinutes,
                                 int heartRate, double distance,
                                 double altitude, int repetitions, double weight, LocalDate date) {

        switch (activityName) {
            case "Corrida":
                Corrida activity = new Corrida(durationInMinutes, userLoggedIn.getId() , distance, heartRate, date);
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

    public LocalDate getDate() { //LocalDate é imutável, logo não é necessário retornar uma cópia
        return date;
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

    public void timeSkip(LocalDate newDate) {
        if (newDate.isAfter(date)) {
            // Atualiza a data do sistema de todos os utilizadores
            for (Map.Entry<Integer, BaseUser> entry : users.entrySet()) {
                entry.getValue().timeSkip(newDate);
            }

            // Atualiza a data do sistema
            date = newDate;

        } else {
            throw new IllegalArgumentException("Data de salto no tempo tem que ser no futuro");
        }
    }

}
