package Controller;

//imports internos
import Model.Model;
import View.View;

public class Controller {
    //esta classe vai ser responsável por controlar o fluxo do programa
    //vai receber ‘input’ do utilizador a partir da View, chamar métodos do Model e devolver ‘output’ para a View
    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        //este método vai ser chamado no método init() da classe Workout_APP
        //vai chamar o método run() da View


//        view.imprimirMenuInial();
//        int opcao = view.lerOpcao();
//        switch (opcao) {
//            etc...

//depois pega no ‘input’ do utilizador e chama o método correspondente do Model
    }
}
