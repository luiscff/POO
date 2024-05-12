package View;


import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class View {

    Scanner sc;
    Logger logger = Logger.getLogger(View.class.getName());
    String separator = "-".repeat(50);

    public View() {
        sc = new Scanner(System.in);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printError(Exception e, String message) {
        logger.severe(e.getMessage());
        System.out.println(message);
    }

    public void printMenu(String[] menu) {
        this.printMenu(menu, "Menu");
    }

    public void printMenu(String[] menu, String message) {
        System.out.println();
        System.out.println(message + ":");
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

    public void printActivitiesList(String[] activities) {
        System.out.println(this.separator);
        System.out.println("Lista de Atividades:");
        for (String activity : activities) {
            System.out.println(activity);
        }
        System.out.println(this.separator);
    }


    public String readString(String message) {
        System.out.print(message);
        return sc.next();
    }


    public LocalDate readDate(String s) {
        this.printMessage(s);
        LocalDate date = null;
        boolean valid_date = false;
        while (!valid_date) {
            try {
                String[] date_parts = sc.next().split("-");
                date = LocalDate.of(Integer.parseInt(date_parts[2]), Integer.parseInt(date_parts[1]), Integer.parseInt(date_parts[0]));
                valid_date = true;
            } catch (Exception e) {
                printError(e, "Por favor insira uma data válida no formato dd-mm-yyyy");
            }
        }
        return date;
    }

    public int readBiggerThanZero(String messageToPrint) {
        this.printMessage(messageToPrint);
        int number = 0;
        boolean valid_number = false;
        while (!valid_number) {
            try {
                number = sc.nextInt();
                if (number <= 0) {
                    throw new Exception("Número inválido");
                }
                valid_number = true;
            } catch (Exception e) {
                printError(e, "Por favor insira um número maior que 0");
            }
        }
        return number;
    }

    public int readMenuOption(int options_count) {
        boolean valid_option = false;
        int option = 0; // opção ‘default’ é o sair
        while (!valid_option){
            System.out.print("Escolha uma opção: ");
            try {
                option = sc.nextInt();
                if (option < 0 || option > options_count) {
                    throw new Exception("Opção inválida");
                }
                valid_option = true;
            } catch (Exception e) {
                printError(e, "Por favor insira uma opção válida");
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



    public int iniciarSessao(Map<Integer,String> users) {

        System.out.println(this.separator);
        System.out.println("Lista de Utilizadores no formato '<id>. <nomeUtilizador>':");
        for (Map.Entry<Integer, String> entry : users.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.println("-".repeat(20));
        System.out.print("Insira o ID do utilizador: ");
        int id = 0;
        boolean valid_id = false;
        while (!valid_id) {
            try {
                id = sc.nextInt();
                if (!users.containsKey(id)) {
                    throw new Exception("Tentativa de Login com ID de utilizador inválido");
                }
                valid_id = true;
            } catch (Exception e) {
                printError(new Exception("ID de utilizador inválido"),
                        "Por favor insira um ID de utilizador existente");
            }

        }
        return id;

    }



}
