/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class Shipment implements Serializable{
    private int id;
    private String method;
    private float cost;
    private String shippingAddress;
    private Payment payment;

    public Shipment() {
    }

    public Shipment(int id, String method, float cost, String shippingAddress, Payment payment) {
        this.id = id;
        this.method = method;
        this.cost = cost;
        this.shippingAddress = shippingAddress;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
