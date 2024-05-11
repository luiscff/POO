package Model;

import Model.Users.AmateurUser;
import Model.Users.BaseUser;
import Model.Users.OccasionalPractitionerUser;
import Model.Users.ProfessionalUser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private Map<Integer, BaseUser> users;

    public Model() {
        this.users = new HashMap<>();
    }

    public void registarUtilizador(String nome, String morada, String email, int freq, String nivel)
            throws IllegalArgumentException{
        switch (nivel) {
            case "Ocasional":
                BaseUser user = new OccasionalPractitionerUser(nome, morada, email, freq);
                addUser(user);
                break;
            case "Amador":
                BaseUser user2 = new AmateurUser(nome, morada, email, freq);
                addUser(user2);
                break;
            case "Profissional":
                BaseUser user3 = new ProfessionalUser(nome, morada, email, freq);
                addUser(user3);
                break;
            default:
                throw new IllegalArgumentException("Nível de experiência inválido ao registar Utilizador: " + nivel);

        }
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

    public String[] getUserList() {
        return users.values().stream().map(BaseUser::getName).toArray(String[]::new);
    }
}
