/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.orderDAO;

import connectionPool.ConnectionPool;
import connectionPool.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicApplication.cartDAO.CartDAOImpl;
import model.cart.Cart;
import model.order.Order;
import model.order.Payment;
import model.order.Shipment;

/**
 *
 * @author pc
 */
public class OrderDAOImpl implements OrderDAO{

    @Override
    public Order addOrder(Order order) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        
        String query1 = "INSERT INTO `order` (CustomerId, CartId, ShipmentId, PaymentId, TotalPrice, Tax, Status, OrderDate) VALUES (?,?,?,?,?,?,?,?)";
        String query2 = "SELECT MAX(Id) AS LatestOrder FROM `order`";
        
        PreparedStatement[] ps = new PreparedStatement[2];
        ResultSet[] rs = new ResultSet[2];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);
        
        try {
            ps[0] = connection.prepareStatement(query1);
            ps[0].setInt(1, order.getCustomer().getId());
            ps[0].setInt(2, order.getCart().getId());
            ps[0].setInt(3, order.getShipment().getId());
            ps[0].setInt(4, order.getPayment().getId());
            ps[0].setFloat(5, order.getTotalPrice());
            ps[0].setFloat(6, order.getTax());
            ps[0].setString(7, order.getStatus());
            ps[0].setDate(8, order.getOrderDate());
            ps[0].executeUpdate();
            
            ps[1] = connection.prepareStatement(query2);
            rs[1] = ps[1].executeQuery();
            rs[1].next();
            int orderId = rs[1].getInt("LatestOrder");
            order.setId(orderId);
            
            return order;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            for (int i = 0 ; i < ps.length; i++){
                DBUtil.closePreparedStatement(ps[i]);
                DBUtil.closeResultSet(rs[i]);
                connectionPool.freeConnection(connection);
            }
        }
    }

    @Override
    public Cart getCart(Cart cart) {
        CartDAOImpl cartDAOImpl = new CartDAOImpl();
        return cartDAOImpl.getCartById(cart.getId());
    }

    @Override
    public Payment getPayment(Payment payment) {
        PaymentDAOImpl paymentDAOImpl = new PaymentDAOImpl();
        return paymentDAOImpl.getPaymentById(payment.getId());
    }

    @Override
    public Shipment getShipment(Shipment shipment) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
