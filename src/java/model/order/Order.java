/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import java.io.Serializable;
import java.sql.Date;
import model.cart.Cart;
import model.customer.Customer;

/**
 *
 * @author pc
 */
public class Order implements Serializable{
    private int id;
    private float totalPrice;
    private float tax;
    private String status;
    private Date orderDate;
    private Customer customer;
    private Cart cart;
    private Shipment shipment;
    private Payment payment;

    public Order() {
    }

    public Order(int id, float totalPrice, float tax, String status, Date orderDate, Customer customer, Cart cart, Shipment shipment, Payment payment) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.tax = tax;
        this.status = status;
        this.orderDate = orderDate;
        this.customer = customer;
        this.cart = cart;
        this.shipment = shipment;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
