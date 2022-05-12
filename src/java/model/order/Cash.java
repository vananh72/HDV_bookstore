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
public class Cash extends Payment implements Serializable{
    private float cashTendered;

    public Cash() {
    }

    public Cash(float cashTendered, int id, String method) {
        super(id, method);
        this.cashTendered = cashTendered;
    }

    public float getCashTendered() {
        return cashTendered;
    }

    public void setCashTendered(float cashTendered) {
        this.cashTendered = cashTendered;
    }

}
