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
import model.order.Payment;
import model.order.Shipment;

/**
 *
 * @author pc
 */
public class ShipmentDAOImpl implements ShipmentDAO {
    
    @Override
    public Shipment addShipment(Shipment shipment) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        
        String query1 = "INSERT INTO shipment (PaymentId, Method, Cost, ShippingAddress) VALUES (?,?,?,?)";
        String query2 = "SELECT MAX(Id) AS LatestShipment FROM shipment";
        PreparedStatement[] ps = new PreparedStatement[2];
        ResultSet[] rs = new ResultSet[2];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);
        
        try {
            ps[0] = connection.prepareStatement(query1);
            ps[0].setInt(1, shipment.getPayment().getId());
            ps[0].setString(2, shipment.getMethod());
            ps[0].setFloat(3, shipment.getCost());
            ps[0].setString(4, shipment.getShippingAddress());
            ps[0].executeUpdate();
            
            ps[1] = connection.prepareStatement(query2);
            rs[1] = ps[1].executeQuery();
            rs[1].next();
            int shipmentId = rs[1].getInt("LatestShipment");
            shipment.setId(shipmentId);
            return shipment;
        } catch (SQLException ex) {
            Logger.getLogger(ShipmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            for (int i = 0; i < ps.length; i++) {
                DBUtil.closePreparedStatement(ps[i]);
                DBUtil.closeResultSet(rs[i]);
                connectionPool.freeConnection(connection);
            }
        }
    }

    @Override
    public Payment getPayment(Payment payment) {
        PaymentDAOImpl paymentDAOImpl = new PaymentDAOImpl();
        return paymentDAOImpl.getPaymentById(payment.getId());
    }
    
}
