package utils;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    Scanner sc;
    Notification notification;
    ArrayList<String> mainMenu;
    ArrayList<String> publisherMenu;
    ArrayList<String> bookMenu;


    public Menu() {
        sc = new Scanner(System.in);
        notification = new Notification();
        mainMenu = new ArrayList<>();
        publisherMenu = new ArrayList<>();
        bookMenu = new ArrayList<>();
        mainMenu.add(Color.YELLOW_BRIGHT + "\t1. Publishers' management");
        mainMenu.add("\t2. Books management" + Color.RESET);
        publisherMenu.add(Color.YELLOW_BRIGHT + "\t\t1.1 Create a Publisher");
        publisherMenu.add("\t\t1.2 Delete the Publisher");
        publisherMenu.add("\t\t1.3 Save the Publishers list to file");
        publisherMenu.add("\t\t1.4 Print the Publisher list from the file" + Color.RESET);
        bookMenu.add(Color.YELLOW_BRIGHT + "\t\t2.1 Create a Book");
        bookMenu.add("\t\t2.2 Search the Book");
        bookMenu.add("\t\t2.3 Update a Book");
        bookMenu.add("\t\t2.4 Delete the Book");
        bookMenu.add("\t\t2.5 Save the Books list to file");
        bookMenu.add("\t\t2.6 Print the Books list from the file" + Color.RESET);
    }

    public void showMainMenu() {
        System.out.println(Color.GREEN_BOLD_BRIGHT + "Choose one of these functions: " + Color.RESET);
        mainMenu.forEach(System.out::println);
    }

    public void showPublisherMenu() {
        System.out.println(Color.GREEN_BOLD_BRIGHT + "Choose one of these functions: " + Color.RESET);
        publisherMenu.forEach(System.out::println);
    }

    public void showBookMenu() {
        System.out.println(Color.GREEN_BOLD_BRIGHT + "Choose one of these functions: " + Color.RESET);
        bookMenu.forEach(System.out::println);
    }


    public int getChoice(int firstChoice, int lastChoice) {
        int choice = 0;
        boolean isInvalid = false;
        do {
            try {
                System.out.print("Enter your choice from " + firstChoice + " to " + lastChoice + ": ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice < firstChoice || choice > lastChoice) {
                    notification.showErrorNoti("Only choose from " + firstChoice + " to " + lastChoice + "!");
                }
                isInvalid = false;
            } catch (Exception e) {
                isInvalid = true;
                notification.showErrorNoti("Not allow string!");
            }
        } while (isInvalid);
        return choice;
    }


}
