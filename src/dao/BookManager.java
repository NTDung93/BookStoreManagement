package dao;

import Database.FileHandler;
import dto.Book;
import dto.Publisher;
import utils.Color;
import utils.Input;
import utils.Notification;
import utils.Validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BookManager {
    ArrayList<Book> listBook;
    ArrayList<Publisher> listPublisher;

    FileHandler fileHandler;

    Notification notification;

    Input input;

    Validation validation;

    public BookManager() {
        fileHandler = new FileHandler();
        listBook = fileHandler.getListBookFromFile();
        listPublisher = fileHandler.getListPublisherFile();
        input = new Input(listPublisher, listBook);
        notification = new Notification();
        validation = new Validation(listPublisher, listBook);
    }

    public void createNewBook() {
        Book newBook = input.createBook();
        if (newBook.getPubId() == null) {
            notification.showErrorNoti("Fail to create new book!");
        } else {
            listBook.add(newBook);
            notification.showSuccessNoti("Created new book successfully!");
        }
        boolean checkContinue = input.checkContinue();
        if (!checkContinue) {
            this.createNewBook();
        } else {
            return;
        }
    }

    public void searchBook() {
        if (listBook.size() == 0) {
            notification.showErrorNoti("Have no book here!");
        } else {
            ArrayList<Book> listFound = new ArrayList<>();
            boolean notFound = true;
            String str = validation.inputString("book's name or publisher'id to search book");


            for (int i = 0; i < listBook.size(); i++) {
//                if (listBook.get(i).getPubId().contains(str) || listBook.get(i).getBookName().toLowerCase().contains(str) || listBook.get(i).getBookName().toUpperCase().contains(str) || listBook.get(i).getBookName().contains(str)) {
                if (listBook.get(i).getPubId().contains(str) || listBook.get(i).getBookName().toLowerCase().contains(str.toLowerCase())) {
                    listFound.add(listBook.get(i));
                    notFound = false;
                }
            }

            if (notFound) {
                notification.showErrorNoti("No book found!");
                return;
            } else {
                Collections.sort(listFound, new Comparator<Book>() {
                    @Override
                    public int compare(Book book1, Book book2) {
                        return (book1.getBookName().compareTo(book2.getBookName()));
                    }
                });
                displayListBook(listFound);
            }
        }
        boolean checkContinue = input.checkContinue();
        if (!checkContinue) {
            this.searchBook();
        } else {
            return;
        }
    }

    public void saveBookListToFile() {
        fileHandler.saveListBookToFIle(listBook);
        notification.showSuccessNoti("Save the Book list to file successfully!");
        boolean checkContinue = input.checkContinue();
        if (!checkContinue) {
            this.saveBookListToFile();
        }
    }

    public void updateBook() {
        int index = input.inputBookIdGetIndex();
        if (index == -1) {
            notification.showErrorNoti("Book's name does not exist!");
            System.out.println();
            notification.showErrorNoti("Update fail!");
        } else {
            Book upBook = listBook.get(index);
            updateEachField(upBook);
            notification.showSuccessNoti("Updated book successfully!");
        }
        boolean checkContinue = input.checkContinue();
        if (!checkContinue) {
            this.updateBook();
        }
    }

    public void deleteBook() {
        int index = input.inputBookIdGetIndex();
        if (index == -1) {
            notification.showErrorNoti("Book's name does not exist!");
            System.out.println();
            notification.showErrorNoti("Delete fail!");
        } else {
            Book delBook = listBook.get(index);
            listBook.remove(delBook);
            notification.showSuccessNoti("Delete book \"" + delBook.getBookName() + "\" successfully!");
        }
        boolean checkContinue = input.checkContinue();
        if (!checkContinue) {
            this.deleteBook();
        }
    }

    public void printListBookFromFile() {
        if (listBook.size() == 0) {
            notification.showErrorNoti("List is empty now!");
        } else {
//            listBook.sort((book1, book2) -> book2.getBookQuantity() - book1.getBookQuantity());
            Collections.sort(listBook, new Comparator<Book>() {
                @Override
                public int compare(Book b1, Book b2) {
                    if (b2.getBookQuantity() == b1.getBookQuantity()) {
                        return (int) (b1.getBookPrice() - b2.getBookPrice());
                    }
                    return b2.getBookQuantity() - b1.getBookQuantity();
                }
            });
            displayListBook(listBook);
        }
        boolean checkContinue = input.checkContinue();
        if (!checkContinue) {
            this.printListBookFromFile();
        } else {
            return;
        }
    }

    public void displayListBook(ArrayList<Book> listBook) {
        System.out.printf("%1s %10s %20s %20s %20s %15s %20s %20s\t\n", Color.CYAN_BOLD_BRIGHT + "No", "Id", "Name", "Price", "Quantity", "Subtotal", "Publisher's Name", "Status" + Color.RESET);
        int ordinalNumber = 1;
        for (Book book : listBook) {
            float sub = book.getBookPrice() * book.getBookQuantity();
            System.out.printf("%1s %15s %20s %17s %15s %16s %17s %28s\t\n", Color.PURPLE_BOLD_BRIGHT + ordinalNumber++, book.getBookId(), book.getBookName(), book.getBookPrice(), book.getBookQuantity(), sub, book.getPubName(), book.getBookStatus() + Color.RESET);
        }
    }

    public void updateEachField(Book upBoob) {
        //update name
        String newName = validation.updateName();
        if (newName != null) {
            upBoob.setBookName(newName);
        } else {
            System.out.println(Color.CYAN_BRIGHT + "Keep the old value!" + Color.RESET);
        }

        //update price
        Float newPrice = validation.updatePrice();
        if (newPrice != null) {
            upBoob.setBookPrice(newPrice);
        } else {
            System.out.println(Color.CYAN_BRIGHT + "Keep the old value!" + Color.RESET);
        }

        //update quantity
        int newQuantity = validation.updateQuantity();
        if (newQuantity != -1) {
            upBoob.setBookQuantity(newQuantity);
        } else {
            System.out.println(Color.CYAN_BRIGHT + "Keep the old value!" + Color.RESET);
        }

        //update status
        String newStatus = validation.updateStatus();
        if (newStatus != null) {
            upBoob.setBookStatus(newStatus);
        } else {
            System.out.println(Color.CYAN_BRIGHT + "Keep the old value!" + Color.RESET);
        }

        //update publisher id
        String newPubId = validation.updatePubId();
        if (newPubId != null) {
            upBoob.setPubId(newPubId);
        } else {
            System.out.println(Color.CYAN_BRIGHT + "Keep the old value!" + Color.RESET);
        }

        //update publisher name according to new publisher id
        for (Book book : listBook) {
            if (book.getPubId().equals(newPubId)) {
                for (Publisher publisher : listPublisher) {
                    if (publisher.getPubId().equals(newPubId)) {
                        book.setPubName(publisher.getPubName());
                    }
                }
            }
        }
    }
}
