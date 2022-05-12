/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.orderDAO;

import model.cart.Cart;
import model.order.Order;
import model.order.Payment;
import model.order.Shipment;

/**
 *
 * @author pc
 */
public interface OrderDAO {
    Order addOrder(Order order);
    Cart getCart(Cart cart);
    Payment getPayment(Payment payment);
    Shipment getShipment(Shipment shipment);
}
