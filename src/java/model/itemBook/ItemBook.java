/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.itemBook;

import java.io.Serializable;
import java.util.List;
import model.book.Book;

/**
 *
 * @author pc
 */
public class ItemBook implements Serializable{
    private String barcode;
    private float price;
    private float discount;
    private String promoText;
    private String description;
    private Book book;
    private List<Image> images;

    public ItemBook() {
    }

    public ItemBook(String barcode, float price, float discount, String promoText, String description, Book book, List<Image> images) {
        this.barcode = barcode;
        this.price = price;
        this.discount = discount;
        this.promoText = promoText;
        this.description = description;
        this.book = book;
        this.images = images;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getPromoText() {
        return promoText;
    }

    public void setPromoText(String promoText) {
        this.promoText = promoText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
