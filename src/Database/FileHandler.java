package Database;

import dto.Book;
import dto.Publisher;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
    public static final String publisherFile = "Publisher.dat";

    public static final String bookFile = "Book.dat";

    public void saveListPublisherToFile(ArrayList<Publisher> listPublisher) {
        try {
            FileOutputStream file = new FileOutputStream(publisherFile); //tạo file trỏ vào pubFile
            ObjectOutputStream output = new ObjectOutputStream(file); //ghi theo Object vào output stream0
//            cho phép chúng ta đọc ghi dữ liệu theo dạng object. Các byte dữ liệu thô sẽ được bao lại bên trong object và chuyển vào object stream.
            output.writeObject(listPublisher); //để ghi listPub theo dạng Object
            file.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Publisher> getListPublisherFile(){
        try {
            FileInputStream file = new FileInputStream(publisherFile); //tạo file trỏ vào pubFile
            ObjectInputStream input = new ObjectInputStream(file); //tạo cái tk sẽ

            return  (ArrayList<Publisher>) input.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void saveListBookToFIle(ArrayList<Book> listBook){
        try {
            FileOutputStream file = new FileOutputStream(bookFile);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(listBook);
            file.close();
            output.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getListBookFromFile(){
        try {
            FileInputStream file = new FileInputStream(bookFile);
            ObjectInputStream input = new ObjectInputStream(file);
            return (ArrayList<Book>) input.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
