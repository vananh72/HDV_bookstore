/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.orderDAO;

import connectionPool.ConnectionPool;
import connectionPool.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.order.Cash;
import model.order.Credit;
import model.order.Payment;

/**
 *
 * @author pc
 */
public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public Payment addPayment(Payment payment) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query0 = "UPDATE payment SET method = ? WHERE Id = ?";
        String query1 = "INSERT INTO cash VALUES (?,?)";
        String query2 = "INSERT INTO credit VALUES (?,?,?,?,?,?)";

        PreparedStatement[] ps = new PreparedStatement[3];
        ResultSet[] rs = new ResultSet[3];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);

        try {
            if (payment.getMethod().equals("Cash")) {
                Cash cash = (Cash) payment;
                ps[0] = connection.prepareStatement(query1);
                ps[0].setFloat(1, cash.getCashTendered());
                ps[0].setInt(2, payment.getId());
                ps[0].executeUpdate();
            } else if (payment.getMethod().equals("Credit")) {
                Credit credit = (Credit) payment;
                ps[1] = connection.prepareStatement(query2);
                ps[1].setString(1, credit.getCardId());
                ps[1].setString(2, credit.getAccountId());
                ps[1].setString(3, credit.getBank());
                ps[1].setString(4, credit.getType());
                ps[1].setDate(5, credit.getExpDate());
                ps[1].setInt(6, payment.getId());
                ps[1].executeUpdate();
            }
            
            ps[2] = connection.prepareStatement(query0);
            ps[2].setString(1, payment.getMethod());
            ps[2].setInt(2, payment.getId());
            ps[2].executeUpdate();
            return payment;
        } catch (SQLException e) {
            e.printStackTrace();
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
    public Payment getPaymentById(int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query1 = "SELECT * FROM payment WHERE Id = ?";
        String query2 = "SELECT * FROM cash WHERE PaymentId = ?";
        String query3 = "SELECT * FROM credit WHERE PaymentId = ?";

        PreparedStatement[] ps = new PreparedStatement[3];
        ResultSet[] rs = new ResultSet[3];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);

        try {
            ps[0] = connection.prepareStatement(query1);
            ps[0].setInt(1, id);
            rs[0] = ps[0].executeQuery();

            rs[0].next();
            String method = rs[0].getString("Method");
            Payment payment = new Payment();
            
            if (method.equals("Not defined")) {
                payment = new Payment(id, method);
            } else if (method.equals("Cash")) {
                ps[1] = connection.prepareStatement(query2);
                ps[1].setInt(1, id);
                rs[1] = ps[1].executeQuery();

                rs[1].next();
                float cashTendered = rs[1].getFloat("CashTendered");
                payment = new Cash(cashTendered, id, method);
            } else if (method.equals("Credit")) {
                ps[2] = connection.prepareStatement(query3);
                ps[2].setInt(1, id);
                rs[2] = ps[2].executeQuery();

                rs[2].next();
                String cardId = rs[2].getString("CardId");
                String accountId = rs[2].getString("AccountId");
                String bank = rs[2].getString("Bank");
                String type = rs[2].getString("Type");
                Date expDate = rs[2].getDate("ExpDate");

                payment = new Credit(cardId, accountId, bank, type, expDate, id, method);
            }

            return payment;
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            for (int i = 0; i < ps.length; i++) {
                DBUtil.closePreparedStatement(ps[i]);
                DBUtil.closeResultSet(rs[i]);
                connectionPool.freeConnection(connection);
            }
        }
    }

}
