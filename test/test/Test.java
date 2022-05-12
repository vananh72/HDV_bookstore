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
public class Test {
    
    public static void main(String[] args) {
        Animal a = new Dog(30, 40, 40, "Cho");
        System.out.println(a instanceof Dog);
    }
}
