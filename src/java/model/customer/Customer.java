/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.customer;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author pc
 */
public class Customer implements Serializable{
    private int id;
    private Account account;
    private FullName fullName;
    private Address address;
    private String tel;
    private Date doB;
    private String sex;

    public Customer() {
    }

    public Customer(int id, Account account, FullName fullName, Address address, String tel, Date doB, String sex) {
        this.id = id;
        this.account = account;
        this.fullName = fullName;
        this.address = address;
        this.tel = tel;
        this.doB = doB;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDoB() {
        return doB;
    }

    public void setDoB(Date doB) {
        this.doB = doB;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    
}
