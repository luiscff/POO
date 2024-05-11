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

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.persistenceManager = new PersistenceManager();
    }

    public void run() {
        //este método vai ser chamado no método init() da classe Workout_APP
        //vai ser responsável por controlar o fluxo do programa


//        try {
//            persistenceManager.saveState("state.bin", model);
//        } catch (IOException e) {
//            view.printError(e, "Erro ao guardar o estado do programa");
//        }
//
//        try {
//            Model model = persistenceManager.loadState("state.bin");
//        } catch (IOException | ClassNotFoundException e) {
//            view.printError(e, "Erro ao carregar o estado do programa");
//        }

        while (true){
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
//                    view.registarUtilizador();
//                    model.registarUtilizador();
                    view.printMessage("Feature ainda não implementada");
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
