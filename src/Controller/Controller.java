package Controller;

//imports internos

import Model.Model;
import View.View;

//imports externos
import java.io.IOException;


public class Controller {
    //esta classe vai ser responsável por controlar o fluxo do programa
    //vai receber ‘input’ do utilizador a partir da View, chamar métodos do Model e devolver ‘output’ para a View
    private Model model;
    private final View view;
    private final PersistenceManager persistenceManager;
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
    }

    public void registarUtilizador() {
        String nome = view.readString("Insira o seu nome: ");
        String email = view.readString("Insira o seu email: ");
        String morada = view.readString("Insira a sua morada: ");
        int freq = view.readFrequenciaCardiaca();

        view.printMessage("Insira o seu nível de experiência: ");
        view.printMenu(menuNiveis);
        int nivel = view.readMenuOption(menuNiveis.length);

        switch (nivel) {
            case 0:
                view.printMessage("Operação cancelada");
                return;
            case 1:
                try {
                    model.registarUtilizador(nome, email, morada, freq, "Ocasional");
                } catch (IllegalArgumentException e) {
                    view.printError(e, "Erro ao registar Utilizador");
                }
                break;
            case 2:
                try {
                    model.registarUtilizador(nome, email, morada, freq, "Amador");
                } catch (IllegalArgumentException e) {
                    view.printError(e, "Erro ao registar Utilizador");
                }
                break;
            case 3:
                try {
                    model.registarUtilizador(nome, email, morada, freq, "Profissional");
                } catch (IllegalArgumentException e) {
                    view.printError(e, "Erro ao registar Utilizador");
                }
                break;
        }

    }

    public void run() {
        //este método vai ser chamado no método init() da classe Workout_APP
        //vai ser responsável por controlar o fluxo do programa


        while (true) {
            view.printMenu(menuInicial);
            int option = view.readMenuOption(menuInicial.length);
            switch (option) {
                case 0: //sair
                    System.exit(0);
                    break;
                case 1: //carregar estado do programa
                    String fileName = view.readFileName();
                    try {
                        model = persistenceManager.loadState(fileName);
                        view.printMessage("Estado do programa carregado com sucesso");
                    } catch (IOException | ClassNotFoundException e) {
                        view.printError(e, "Erro ao carregar o estado do programa");
                    }
                    break;
                case 2:
                    this.registarUtilizador();
                    view.printMessage("Utilizador registado com sucesso");
                    break;
                case 3:
//                    view.iniciarSessao();
//                    this.runUser();
                    view.printMessage("Feature ainda não implementada");
                    break;
                case 4:
                    //avançar no tempo
                    view.printMessage("Feature ainda não implementada");
                    break;
                case 5:
                    //estatísticas
                    view.printMessage("Feature ainda não implementada");
                    break;
                case 6: //guardar estado do programa
                    String fileName2 = view.readFileName();
                    try {
                        persistenceManager.saveState(fileName2, model);
                        view.printMessage("Estado do programa guardado com sucesso");
                    } catch (IOException e) {
                        view.printError(e, "Erro ao guardar o estado do programa");
                    }
                    break;
            }
        }

    }
}
