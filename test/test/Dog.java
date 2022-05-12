/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author pc
 */
public class Dog extends Animal{
    private int height;
    private float weight;

    public Dog(int height, float weight, int id, String name) {
        super(id, name);
        this.height = height;
        this.weight = weight;
    }
    
}
