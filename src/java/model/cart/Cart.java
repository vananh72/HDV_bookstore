/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.cart;

import java.io.Serializable;
import java.util.List;
import model.order.Payment;

/**
 *
 * @author pc
 */
public class Cart implements Serializable{
    private int id;
    private List<SelectedItems> selectedItems;
    private int totalAmount;
    private float totalPrice;
    private Payment payment;

    public Cart() {
    }

    public Cart(int id, List<SelectedItems> selectedItems, int totalAmount, float totalPrice, Payment payment) {
        this.id = id;
        this.selectedItems = selectedItems;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SelectedItems> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<SelectedItems> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
}
