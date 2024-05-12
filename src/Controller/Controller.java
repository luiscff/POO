package Controller;

//imports internos

import Model.Model;
import View.View;

//imports externos
import java.io.IOException;
import java.util.Map;


public class Controller {
    //esta classe vai ser responsável por controlar o fluxo do programa
    //vai receber ‘input’ do utilizador a partir da View, chamar métodos do Model e devolver ‘output’ para a View
    private Model model;
    private final View view;
    private final PersistenceManager persistenceManager;
    private ControllerActivity controllerActivity;




    private final String[] menuInicial = {"Carregar estado do programa", "Registar Utilizador", "Iniciar Sessão",
            "Avançar no tempo", "Estatísticas", "Guardar estado do programa"};
    private final String[] menuUtilizador = {"Consultar Atividades", "Consultar Plano de Treino",
            "Registar nova Atividade", "Editar Atividade", "Editar Plano de Treino", "Editar Perfil",
            "Guardar estado do programa"};
    private final String[] menuNiveis = {"Ocasional", "Amador", "Profissional"};

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.persistenceManager = new PersistenceManager();
        this.controllerActivity = new ControllerActivity(model, view);
    }

    public void guardarEstado() {
        String fileName = view.readFileName();
        try {
            persistenceManager.saveState(fileName, model);
            view.printMessage("Estado do programa guardado com sucesso");
        } catch (IOException e) {
            view.printError(e, "Erro ao guardar o estado do programa");
        }
    }

    public void registarUtilizador() {
        String nome = view.readString("Insira o seu nome: ");
        String email = view.readString("Insira o seu email: ");
        String morada = view.readString("Insira a sua morada: ");

        view.printMenu(menuNiveis, "Insira o seu nível de experiência");
        int nivel = view.readMenuOption(menuNiveis.length);

        switch (nivel) {
            case 0:
                view.printMessage("Operação cancelada");
                return;
            case 1:
                try {
                    model.registarUtilizador(nome, email, morada, "Ocasional");
                } catch (IllegalArgumentException e) {
                    view.printError(e, "Erro ao registar Utilizador");
                }
                break;
            case 2:
                try {
                    model.registarUtilizador(nome, email, morada, "Amador");
                } catch (IllegalArgumentException e) {
                    view.printError(e, "Erro ao registar Utilizador");
                }
                break;
            case 3:
                try {
                    model.registarUtilizador(nome, email, morada, "Profissional");
                } catch (IllegalArgumentException e) {
                    view.printError(e, "Erro ao registar Utilizador");
                }
                break;
        }

    }


    public void runUtilizador() {
        while (true) {
            view.printMenu(menuUtilizador, "Menu pessoal de " + model.getLoggedInUserName());
            int option = view.readMenuOption(menuUtilizador.length);
            switch (option) {
                case 0: //sair
                    System.exit(0);
                    break;
                case 1: //consultar atividades
                    view.printActivitiesList(model.getActivitiesList());
                    break;
                case 2: //consultar plano de treino
                    view.printMessage("Feature ainda não implementada");
                    break;
                case 3: //registar nova atividade
                    controllerActivity.registarActivity();
                    break;
                case 4: //editar atividade
                    view.printMessage("Feature ainda não implementada");
                    break;
                case 5: //editar plano de treino
                    view.printMessage("Feature ainda não implementada");
                    break;
                case 6: //editar perfil
                    view.printMessage("Feature ainda não implementada");
                    break;
                case 7: //guardar estado do programa
                    this.guardarEstado();
                    break;
            }
        }
    }

    public void run() {
        //este método vai ser chamado no método init() da classe Workout_APP
        //vai ser responsável por controlar o fluxo do programa


        while (true) {
            view.printMenu(menuInicial, "MENU INICIAL");
            int option = view.readMenuOption(menuInicial.length);
            switch (option) {
                case 0: //sair
                    System.exit(0);
                    break;
                case 1: //carregar estado do programa
                    String fileName = view.readFileName();
                    try {
                        model = persistenceManager.loadState(fileName);

                        //TODO: AVISO - Todos os outros controllers têm de ser atualizados ao carrergar o estado
                        controllerActivity = new ControllerActivity(model, view);
                        //controller_outro = new Controller_outro(model, view);
                        //etc...

                        view.printMessage("Estado do programa carregado com sucesso");
                    } catch (IOException | ClassNotFoundException e) {
                        view.printError(e, "Erro ao carregar o estado do programa");
                    }
                    break;
                case 2: //registar utilizador
                    this.registarUtilizador();
                    view.printMessage("Utilizador registado com sucesso");
                    break;
                case 3: //iniciar sessão
                    Map<Integer, String> userNames = model.getUserNames();
                    int userId = view.iniciarSessao(userNames);
                    model.login(userId);
                    view.printMessage("Sessão iniciada com sucesso");
                    this.runUtilizador();
                case 4: //avançar no tempo
                    view.printMessage("Feature ainda não implementada");
                    break;
                case 5: //estatísticas
                    view.printMessage("Feature ainda não implementada");
                    break;
                case 6: //guardar estado do programa
                    this.guardarEstado();
                    break;
            }
        }

    }
}
