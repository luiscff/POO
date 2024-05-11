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
        for (int i = 0; i < longest_option; i++) System.out.print("-"); //prints a line of dashes
        System.out.println();
    }

    public int readMenuOption(int options_count) {
        boolean valid_option = false;
        int option = 0; // opção ‘default’ é o sair
        while (!valid_option){
            System.out.print("Enter an option: ");
            try {
                option = sc.nextInt();
                if (option < 0 || option > options_count) {
                    throw new Exception("Invalid option");
                }
                valid_option = true;
            } catch (Exception e) {
                System.out.println("Please enter a valid option");
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

}
