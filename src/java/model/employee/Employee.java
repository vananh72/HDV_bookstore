/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.employee;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author pc
 */
public class Employee implements Serializable{
    private int id;
    private Account account;
    private Address address;
    private FullName fullName;
    private String tel;
    private Date doB;
    private String sex;
    private String role;

    public Employee() {
    }

    public Employee(int id, Account account, Address address, FullName fullName, String tel, Date doB, String sex, String role) {
        this.id = id;
        this.account = account;
        this.address = address;
        this.fullName = fullName;
        this.tel = tel;
        this.doB = doB;
        this.sex = sex;
        this.role = role;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
