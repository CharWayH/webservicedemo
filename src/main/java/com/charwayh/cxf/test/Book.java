package com.charwayh.cxf.test;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Book POJO类
 */
@XmlRootElement
public class Book {
    private long id;
    private String bookName;
    private double price;

    public Book() {
    }

    public Book(long id, String bookName, double price) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                '}';
    }
}
