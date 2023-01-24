package dto;

import java.io.Serializable;

public class Book implements Serializable {
    String bookId, bookName, pubId, bookStatus, pubName;
    Float bookPrice;
    int bookQuantity;

    public Book(String bookId, String bookName, String pubId, String pubName, String bookStatus, Float bookPrice, int bookQuantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.pubId = pubId;
        this.bookStatus = bookStatus;
        this.bookPrice = bookPrice;
        this.bookQuantity = bookQuantity;
        this.pubName = pubName;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPubId() {
        return pubId;
    }

    public void setPubId(String pubId) {
        this.pubId = pubId;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}
