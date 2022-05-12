/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.orderDAO;

import model.order.Payment;

/**
 *
 * @author pc
 */
public interface PaymentDAO {
    Payment addPayment(Payment payment);
    Payment getPaymentById(int id);
}
