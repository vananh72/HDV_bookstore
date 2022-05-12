/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.order;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author pc
 */
public class Credit extends Payment implements Serializable{
    private String cardId;
    private String accountId;
    private String bank;
    private String type;
    private Date expDate;

    public Credit() {
    }

    public Credit(String cardId, String accountId, String bank, String type, Date expDate, int id, String method) {
        super(id, method);
        this.cardId = cardId;
        this.accountId = accountId;
        this.bank = bank;
        this.type = type;
        this.expDate = expDate;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
    
}
