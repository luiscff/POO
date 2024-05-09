package View;

public class View {

    public void printError(Exception e, String message) {
        System.out.println(message);
        e.printStackTrace();
    }

}
