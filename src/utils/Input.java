package utils;

import Database.FileHandler;
import dto.Book;
import dto.Publisher;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    ArrayList<Publisher> listPublisher;

    ArrayList<Book> listBook;

    Scanner sc;

    Notification notification;

    Validation validation;
    FileHandler fileHandler;

    public Input(ArrayList<Publisher> listPublisher, ArrayList<Book> listBook) {
        this.listPublisher = listPublisher;
        this.listBook = listBook;
        sc = new Scanner(System.in);
        notification = new Notification();
        validation = new Validation(listPublisher, listBook);
    }

    public Publisher createPublisher() {
        String pubId = validation.inputPubId();
        String pubName = validation.inputName("publisher");
        String pubPhoneNumber = validation.inputPhoneNumber();
        Publisher newPublisher = new Publisher(pubId, pubName, pubPhoneNumber);
        return newPublisher;
    }

    public Book createBook(){
        String bookId = validation.inputBookId();
        String bookName = validation.inputName("book");
        Float bookPrice = validation.inputBookPrice();
        int bookQuantity = validation.inputBookQuantity();
        String bookStatus = validation.inputBookStatus();
        String pubId;
        String pubName = null;
        int index = inputPubIdGetIndex();
        if (index == -1) {
            notification.showErrorNoti("Publisher's id does not exist!");
            pubId = null;
            System.out.println();
        } else {
            Publisher publisher = listPublisher.get(index);
            pubId = publisher.getPubId();
            pubName = listPublisher.get(index).getPubName();
        }
        Book newBook = new Book(bookId, bookName, pubId, pubName, bookStatus, bookPrice, bookQuantity);
        return newBook;
    }

    public boolean checkContinue() {
        String value;
//        boolean isInvalid = false;
        do {
            System.out.print(Color.BLUE_BRIGHT + "Back to the menu [\"Yes\" or \"No\"]: " + Color.RESET);
            value = sc.nextLine();
            if (value.isEmpty()) {
                notification.showErrorNoti("Not allow empty!");
//                isInvalid = true;
            }
            if (!value.equalsIgnoreCase("yes") && !value.equalsIgnoreCase("no")) {
                notification.showErrorNoti("Only enter \"Yes\" or \"No\"!");
//                isInvalid = true;
            }
        } while (value.isEmpty() || (!value.equalsIgnoreCase("yes") && !value.equalsIgnoreCase("no")));
        return value.equalsIgnoreCase("yes");
    }

    public int inputPubIdGetIndex(){
        String id = validation.inputString("Publisher's id");
        for (Publisher publisher : listPublisher){
            if (publisher.getPubId().equals(id)){
                return listPublisher.indexOf(publisher);
            }
        }
        return -1;
    }

    public int inputBookIdGetIndex(){
        String id = validation.inputString("Book's id");
        for (Book book : listBook){
            if (book.getBookId().equals(id)){
                return listBook.indexOf(book);
            }
        }
        return -1;
    }

}
