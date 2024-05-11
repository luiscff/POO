package View;

import java.util.Scanner;
import java.util.logging.Logger;

public class View {

    Scanner sc;
    Logger logger = Logger.getLogger(View.class.getName());

    public View() {
        sc = new Scanner(System.in);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printError(Exception e, String message) {
        System.out.println(message);
        logger.severe(e.getMessage());
    }

    public void printMenu(String[] menu) {
        System.out.println();
        System.out.println("MENU:");
        int longest_option = 0;
        for (int i = 0; i < menu.length; i++) { //prints each option
            if (menu[i].length() > longest_option) { //updates longest_option
                longest_option = menu[i].length();
            }
            System.out.print((i+1) + ". ");
            System.out.println(menu[i]);
        }
        System.out.println(0 + ". Sair");
        for (int i = 0; i < longest_option + 3 ; i++) System.out.print("-"); //prints a line of dashes
        System.out.println();
    }


    public String readString(String message) {
        System.out.print(message);
        return sc.next();
    }
    public int readMenuOption(int options_count) {
        boolean valid_option = false;
        int option = 0; // opção ‘default’ é o sair
        while (!valid_option){
            System.out.print("Escolha uma opção: ");
            try {
                option = sc.nextInt();
                if (option < 0 || option > options_count) {
                    printError(new Exception("Opção inválida"), "Por favor insira uma opção válida");
                }
                valid_option = true;
            } catch (Exception e) {
                System.out.println("Por favor insira uma opção válida");
            }
        }
        return option;

    }

    public String readFileName() {
        System.out.print("Insere o nome do Ficheiro: ");
        String fileName = sc.next();
        while (!fileName.endsWith(".bin")) {
            System.out.println("Nome de ficheiro tem de terminar em '.bin'");
            System.out.print("Insere o nome do Ficheiro: ");
            fileName = sc.next();
        }
        return fileName;
    }

    public int readFrequenciaCardiaca() {
        System.out.print("Insira a sua frequência cardíaca média (bpm): ");
        int frequenciaCardiaca = 0;
        boolean valid_frequencia = false;
        while (!valid_frequencia) {
            try {
                frequenciaCardiaca = sc.nextInt();
                if (frequenciaCardiaca < 0) {
                    printError(new Exception("Frequência cardíaca inválida"),
                            "Por favor insira uma frequência cardíaca válida");
                }
                valid_frequencia = true;
            } catch (Exception e) {
                System.out.println("Por favor insira uma frequência cardíaca válida");
            }
        }
        return frequenciaCardiaca;
    }



}
