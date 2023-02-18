package utils;

import dto.Book;
import dto.Publisher;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Validation {
    ArrayList<Publisher> listPublisher;
    ArrayList<Book> listBook;
    Scanner sc;
    Notification notification;

    public Validation(ArrayList<Publisher> listPublisher, ArrayList<Book> listBook) {
        this.listPublisher = listPublisher;
        this.listBook = listBook;
        sc = new Scanner(System.in);
        notification = new Notification();
    }

    public String inputPubId() {
        String pubId;
        String regex = "^P([0-9]{5})$";
        do {
            System.out.print("Enter Publisher's id with \"Pxxxxx\" format: ");
            pubId = sc.nextLine();
            if (pubId.isEmpty()) {
                notification.showErrorNoti("Not allow empty!");
                continue;
            }
            if (!pubId.trim().matches(regex)) {
                notification.showErrorNoti("Wrong id's format!");
            }
            if (checkDuplicatePubId(pubId.trim())) {
                notification.showErrorNoti("Not allow duplicate!");
            }
        } while (pubId.isEmpty() || !pubId.trim().matches(regex) || checkDuplicatePubId(pubId.trim()));
        return pubId;
    }

    public boolean checkDuplicatePubId(String id) {
        for (Publisher publisher : listPublisher) {
            if (publisher.getPubId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String inputName(String label) {
        String name;
        do {
            System.out.print("Enter name of " + label + ": ");
            name = sc.nextLine();
            if (name.isEmpty()) {
                notification.showErrorNoti("Not allow empty!");
                continue;
            }
            if (name.length() < 5 || name.length() > 30) {
                notification.showErrorNoti("Only enter name has length from 5 to 30 characters!");
            }
        } while (name.isEmpty() || (name.length() < 5 || name.length() > 30));
        return name;
    }

    public String inputPhoneNumber() {
        String phoneNumber;
        String regex = "^[0-9]{10}|[0-9]{11}|[0-9]{12}$";
        do {
            System.out.print("Enter publisher's phone number: ");
            phoneNumber = sc.nextLine();
            if (!phoneNumber.trim().matches(regex)) {
                notification.showErrorNoti("PLease enter phone number has length from 10 to 12!");
            }
        } while (!phoneNumber.trim().matches(regex));
        return phoneNumber;
    }

    public String inputBookId() {
        String bookId;
        String regex = "^B([0-9]{5})$";
        do {
            System.out.print("Enter Book's id with \"Bxxxxx\" format: ");
            bookId = sc.nextLine();
            if (bookId.isEmpty()) {
                notification.showErrorNoti("Not allow empty!");
                continue;
            }
            if (!bookId.trim().matches(regex)) {
                notification.showErrorNoti("Wrong id's format!");
            }
            if (checkDuplicateBookId(bookId.trim())) {
                notification.showErrorNoti("Not allow duplicate!");
            }
        } while (bookId.isEmpty() || !bookId.trim().matches(regex) || checkDuplicateBookId(bookId.trim()));
        return bookId;
    }

    public float inputBookPrice() {
        float price = 0;
        boolean isInvalid = false;
        do {
            try {
                System.out.print("Enter price of the book: ");
                price = Float.parseFloat(sc.nextLine());
                if (price <= 0) {
                    notification.showErrorNoti("Please enter real number and greater than 0!");
                }
                isInvalid = false;
            } catch (Exception e) {
                notification.showErrorNoti("Please enter real number and greater than 0!");
                isInvalid = true;
            }
        } while (isInvalid || price <= 0);
        return price;
    }

    public int inputBookQuantity() {
        int quantity = 0;
        boolean isInvalid = false;
        do {
            try {
                System.out.print("Enter quantity of the book: ");
                quantity = Integer.parseInt(sc.nextLine());
                if (quantity <= 0) {
                    notification.showErrorNoti("Please enter integer number and greater than 0!");
                }
                isInvalid = false;
            } catch (Exception e) {
                notification.showErrorNoti("Please enter integer number and greater than 0!");
                isInvalid = true;
            }
        } while (isInvalid || quantity <= 0);
        return quantity;
    }

    public String inputBookStatus() {
        String status = null;
        String realStatus = null;
        do {
            System.out.print("Enter status of the book [Available (A) or Not Available (NA)]: ");
            status = sc.nextLine();
            if (status.isEmpty()) {
                notification.showErrorNoti("Not allow empty!");
                continue;
            }
            if (!status.trim().equalsIgnoreCase("A") && !status.trim().equalsIgnoreCase("NA")) {
                notification.showErrorNoti("Only enter \"A\" or \"NA\"!");
            }
        } while (status.isEmpty() || (!status.trim().equalsIgnoreCase("A") && !status.trim().equalsIgnoreCase("NA")));
        if (status.trim().equalsIgnoreCase("A")) {
            realStatus = "Available";
        } else {
            realStatus = "Not Available";
        }
        return realStatus;
    }

    public boolean checkDuplicateBookId(String id) {
        for (Book book : listBook) {
            if (book.getBookId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String inputString(String label) {
        String str;
        do {
            System.out.print("Enter " + label + ": ");
            str = sc.nextLine();
            if (str.isEmpty()) {
                notification.showErrorNoti("Not allow empty!");
            }
        } while (str.isEmpty());
        return str;
    }

    public String updateName(){
        String name;
        do {
            System.out.print("Enter new name of the book: ");
            name = sc.nextLine();
            if (name.trim().isEmpty()) {
                return null;
            }
            if (name.length() < 5 || name.length() > 30) {
                notification.showErrorNoti("Only enter name has length from 5 to 30 characters!");
            }
        } while (name.length() < 5 || name.length() > 30);
        return name;
    }

    public Float updatePrice(){
        float price = 0;
        boolean isInvalid = false;
        do {
            try {
                System.out.print("Enter new price of the book: ");
                price = Float.parseFloat(sc.nextLine());
                if (price <= 0) {
                    notification.showErrorNoti("Please enter real number and greater than 0!");
                }
                isInvalid = false;
            } catch (Exception e) {
                return null;
            }
        } while (isInvalid || price <= 0);
        return price;
    }

    public int updateQuantity(){
        int quantity = 0;
        boolean isInvalid = false;
        do {
            try {
                System.out.print("Enter new quantity of the book: ");
                quantity = Integer.parseInt(sc.nextLine());
                if (quantity <= 0) {
                    notification.showErrorNoti("Please enter integer number and greater than 0!");
                }
                isInvalid = false;
            } catch (Exception e) {
                return -1;
            }
        } while (isInvalid || quantity <= 0);
        return quantity;
    }

    public String updateStatus(){
        String status = null;
        String realStatus = null;
        do {
            System.out.print("Enter new status of the book [Available (A) or Not Available (NA)]: ");
            status = sc.nextLine();
            if (status.trim().isEmpty()) {
                return null;
            }
            if (!status.trim().equalsIgnoreCase("A") && !status.trim().equalsIgnoreCase("NA")) {
                notification.showErrorNoti("Only enter \"A\" or \"NA\"!");
            }
        } while ((!status.trim().equalsIgnoreCase("A") && !status.trim().equalsIgnoreCase("NA")));
        if (status.trim().equalsIgnoreCase("A")) {
            realStatus = "Available";
        } else {
            realStatus = "Not Available";
        }
        return realStatus;
    }

    public String updatePubId()     {
        String pubId;
        String regex = "^P([0-9]{5})$";
        do {
            System.out.print("Enter Publisher's id with \"Pxxxxx\" format: ");
            pubId = sc.nextLine();
            if (pubId.isEmpty()) {
                return null;
            }
            if (!pubId.trim().matches(regex)) {
                notification.showErrorNoti("Wrong id's format!");
            }
        } while (!pubId.trim().matches(regex));
        return pubId;
    }
}
