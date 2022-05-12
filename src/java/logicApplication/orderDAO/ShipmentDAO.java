/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.orderDAO;

import model.order.Payment;
import model.order.Shipment;

/**
 *
 * @author pc
 */
public interface ShipmentDAO {
    Shipment addShipment(Shipment shipment);
    Payment getPayment(Payment payment);
}
