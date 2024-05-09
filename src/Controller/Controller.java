package Controller;

//imports internos
import Model.Model;
import View.View;

//imports externos
import java.io.IOException;


public class Controller {
    //esta classe vai ser responsável por controlar o fluxo do programa
    //vai receber ‘input’ do utilizador a partir da View, chamar métodos do Model e devolver ‘output’ para a View
    private final Model model;
    private final View view;
    private final PersistenceManager persistenceManager;


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.persistenceManager = new PersistenceManager();
    }

    public void run() {
        //este método vai ser chamado no método init() da classe Workout_APP


        try {
            persistenceManager.saveState("state.bin", model);
        } catch (IOException e) {
            view.printError(e, "Erro ao guardar o estado do programa");
        }

        try {
            Model model = persistenceManager.loadState("state.bin");
        } catch (IOException | ClassNotFoundException e) {
            view.printError(e, "Erro ao carregar o estado do programa");
        }



//        view.imprimirMenuInial();
//        int opcao = view.lerOpcao();
//        switch (opcao) {
//            case 1:
//                persistenceManager.save(model);
//                break;
//            case 2:
//                model = persistenceManager.load();
//                break;
//            case 3:
//                          etc...
    }
}
