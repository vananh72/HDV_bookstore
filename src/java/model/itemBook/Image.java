/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.itemBook;

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class Image implements Serializable{
    private int id;
    private String src;

    public Image() {
    }

    public Image(int id, String src) {
        this.id = id;
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    
}
