/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cart;

import java.io.Serializable;
import model.itemBook.ItemBook;

/**
 *
 * @author pc
 */
public class SelectedItems implements Serializable{
    private int id;
    private ItemBook itemBook;
    private int quantity;

    public SelectedItems() {
    }

    public SelectedItems(int id, ItemBook itemBook, int quantity) {
        this.id = id;
        this.itemBook = itemBook;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemBook getItemBook() {
        return itemBook;
    }

    public void setItemBook(ItemBook itemBook) {
        this.itemBook = itemBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
