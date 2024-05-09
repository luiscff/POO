import Controller.Controller;
import Model.Model;
import View.View;



public class ExerciseTracker_APP {
    //esta vai ser a classe principal do programa:
    //vai inicializar o Model, o View e o Controller e vai chamar o método run() do Controller

    //o objeto ExerciseTracker_APP vai ser guardado no ficheiro binario de modo a ser possivel carregar
    //a aplicação toda de uma vez (assim pode se guardar em qualquer altura do programa)

    public ExerciseTracker_APP() {
    }

    public void init() {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.run();
    }
}
