/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.cartDAO;

import connectionPool.ConnectionPool;
import connectionPool.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicApplication.itemBookDAO.ItemBookDAOImpl;
import logicApplication.orderDAO.PaymentDAOImpl;
import model.cart.Cart;
import model.cart.SelectedItems;
import model.itemBook.ItemBook;
import model.order.Payment;

/**
 *
 * @author pc
 */
public class CartDAOImpl implements CartDAO {

    @Override
    public Cart createCart() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query1 = "INSERT INTO payment (Method) VALUES (?)";
        String query2 = "SELECT MAX(Id) AS LatestPayment FROM payment";
        String query3 = "INSERT INTO cart (PaymentId, TotalAmount, TotalPrice) VALUES (?,?,?)";
        String query4 = "SELECT MAX(Id) AS LatestCart FROM cart";

        PreparedStatement[] ps = new PreparedStatement[4];
        ResultSet[] rs = new ResultSet[4];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);

        try {
            ps[0] = connection.prepareStatement(query1);
            ps[0].setString(1, "Not defined");
            ps[0].executeUpdate();

            ps[1] = connection.prepareStatement(query2);
            rs[1] = ps[1].executeQuery();
            rs[1].next();
            int paymentId = rs[1].getInt("LatestPayment");
            Payment payment = new Payment(paymentId, "Not defined");

            ps[2] = connection.prepareStatement(query3);
            ps[2].setInt(1, paymentId);
            ps[2].setInt(2, 0);
            ps[2].setInt(3, 0);
            ps[2].executeUpdate();

            ps[3] = connection.prepareStatement(query4);
            rs[3] = ps[3].executeQuery();
            rs[3].next();
            int cartId = rs[3].getInt("LatestCart");
            List<SelectedItems> selectedItems = new ArrayList<>();
            Cart cart = new Cart(cartId, selectedItems, 0, 0, payment);

            return cart;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public Cart getCartById(int id) {
        PaymentDAOImpl paymentDAOImpl = new PaymentDAOImpl();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query = "SELECT * FROM cart WHERE Id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            rs.next();
            int paymentId = rs.getInt("PaymentId");
            int totalAmount = rs.getInt("TotalAmount");
            float totalPrice = rs.getFloat("TotalPrice");

            List<SelectedItems> selectedItems = getItemsOfCart(id);
            Payment payment = paymentDAOImpl.getPaymentById(paymentId);

            Cart cart = new Cart(id, selectedItems, totalAmount, totalPrice, payment);
            return cart;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public boolean addItemBookToCart(ItemBook itemBook, Cart cart, int quantity) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query1 = "SELECT * FROM selecteditems WHERE ItemBookBarcode = ? AND CartId = ?";
        String query2 = "INSERT INTO selecteditems (Quantity, ItemBookBarcode, CartId) VALUES (?,?,?)";
        String query3 = "UPDATE selecteditems SET Quantity = Quantity + ? WHERE CartId = ? AND ItemBookBarcode = ?";
        String query4 = "UPDATE cart SET TotalAmount = TotalAmount + ?, TotalPrice = TotalPrice + ? WHERE Id = ?";

        PreparedStatement[] ps = new PreparedStatement[4];
        ResultSet[] rs = new ResultSet[4];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);

        try {
            ps[0] = connection.prepareStatement(query1);
            ps[0].setString(1, itemBook.getBarcode());
            ps[0].setInt(2, cart.getId());
            rs[0] = ps[0].executeQuery();
            
            if(rs[0].next() == false){
                ps[1] = connection.prepareStatement(query2);
                ps[1].setInt(1, quantity);
                ps[1].setString(2, itemBook.getBarcode());
                ps[1].setInt(3, cart.getId());
                ps[1].executeUpdate();
            } else {
                ps[2] = connection.prepareStatement(query3);
                ps[2].setInt(1, quantity);
                ps[2].setInt(2, cart.getId());
                ps[2].setString(3, itemBook.getBarcode());
                ps[2].executeUpdate();
            }
            
            ps[3] = connection.prepareStatement(query4);
            ps[3].setInt(1, quantity);
            ps[3].setFloat(2, itemBook.getPrice() * quantity);
            ps[3].setInt(3, cart.getId());
            ps[3].executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            for (int i = 0; i < ps.length; i++){
                DBUtil.closePreparedStatement(ps[i]);
                DBUtil.closeResultSet(rs[i]);
                connectionPool.freeConnection(connection);
            }
        }

    }
    
    @Override
    public List<SelectedItems> getItemsOfCart(int cartId) {
        ItemBookDAOImpl itemBookDAOImpl = new ItemBookDAOImpl();

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query1 = "SELECT * FROM selecteditems WHERE CartId = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(query1);
            ps.setInt(1, cartId);
            rs = ps.executeQuery();

            List<SelectedItems> selectedItems = new ArrayList<>();
            while (rs.next()) {
                int selectedItemsId = rs.getInt("Id");
                int quantity = rs.getInt("Quantity");
                String itemBookBarcode = rs.getString("ItemBookBarcode");

                ItemBook itemBook = itemBookDAOImpl.getItemBookByCode(itemBookBarcode);
                SelectedItems st = new SelectedItems(selectedItemsId, itemBook, quantity);
                selectedItems.add(st);
            }

            return selectedItems;
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public Payment getPayment(Payment payment) {
        PaymentDAOImpl paymentDAOImpl = new PaymentDAOImpl();
        return paymentDAOImpl.getPaymentById(payment.getId());
    }

    @Override
    public boolean removeItemBookFromCart(Cart cart, SelectedItems selectedItem) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        
        String query = "DELETE FROM selecteditems WHERE Id = ?";
        
        PreparedStatement ps = null;
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, selectedItem.getId());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public boolean changeQuantity(Cart cart, SelectedItems selectedItem) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        
        String query = "UPDATE selecteditems SET Quantity = ? WHERE Id = ?";
        
        PreparedStatement ps = null;
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, selectedItem.getQuantity());
            ps.setInt(2, selectedItem.getId());
            
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            connectionPool.freeConnection(connection);
        }
    }

}
