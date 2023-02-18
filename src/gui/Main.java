package gui;

import dao.BookManager;
import dao.PublisherManager;
import utils.Color;
import utils.Menu;
import utils.Notification;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Notification notification = new Notification();
    static Menu menu = new Menu();
    static PublisherManager publisherManager = new PublisherManager();
    static BookManager bookManager = new BookManager(publisherManager.getListPublisher());
    //bắt dc null trong display listBook khi có pubId bị xóa

    public static void main(String[] args) {
        int choice = 0;
        boolean quit = false;//flag to decide when will quit
        do {
            menu.showMainMenu();
            System.out.print(Color.WHITE_BRIGHT + "Enter your choice: " +Color.RESET);
            try {
                choice = Integer.parseInt(sc.nextLine());
                //only choose 1 or 2, others will exit the program
            } catch (Exception e) {
                quit = true; //catch when user input char or string
            }
            if (!quit) { //quit == false
                switch (choice) {
                    case 1:
                        controlPublisherMenu();
                        break;
                    case 2:
                        controlBookMenu();
                        break;
                    default: //if user input an integer but not 1 or 2
                        quit = true;
                        notification.showSuccessNoti("Thank you for using my application!");
                        break;
                }
            } else { //quit == true
                notification.showSuccessNoti("Thank you for using my application!");
            }
        } while (!quit); //quit when quit == true
    }

    public static void controlPublisherMenu() {
        int choice = 0;
        boolean quit = false;
        do {
            menu.showPublisherMenu();
            System.out.print(Color.WHITE_BRIGHT + "Enter your choice from 1 to 4: " +Color.RESET);
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                quit = true;
            }
            if (!quit) {
                switch (choice) {
                    case 1:
                        publisherManager.createNewPublisher();
                        break;
                    case 2:
                        publisherManager.deletePublisher();
                        break;
                    case 3:
                        publisherManager.savePubListToFile();
                        break;
                    case 4:
                        publisherManager.printListPub();
                        break;
                    default:
                        quit = true;
                        notification.showSuccessNoti("Thank you for using Publisher Management!");
                        break;
                }
            } else {
                notification.showSuccessNoti("Thank you for using Publisher Management!");
            }
        } while (!quit);
    }

    public static void controlBookMenu() {
        int choice = 0;
        boolean quit = false;
        do {
            menu.showBookMenu();
            System.out.print(Color.WHITE_BRIGHT + "Enter your choice from 1 to 6 : " +Color.RESET);
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                quit = true;
            }
            if (!quit) {
                switch (choice) {
                    case 1:
                        bookManager.createNewBook();
                        break;
                    case 2:
                        bookManager.searchBook();
                        break;
                    case 3:
                        bookManager.updateBook();
                        break;
                    case 4:
                        bookManager.deleteBook();
                        break;
                    case 5:
                        bookManager.saveBookListToFile();
                        break;
                    case 6:
                        bookManager.printListBookFromFile();
                        break;
                    default:
                        quit = true;
                        notification.showSuccessNoti("Thank you for using Books Management!");
                        break;
                }
            } else {
                notification.showSuccessNoti("Thank you for using Books Management!");
            }
        } while (!quit);
    }
}
