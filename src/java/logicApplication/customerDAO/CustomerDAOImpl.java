/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.customerDAO;

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
import model.customer.Account;
import model.customer.Address;
import model.customer.Customer;
import model.customer.FullName;

/**
 *
 * @author pc
 */
public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer checkLogin(Account account) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query1 = "SELECT * FROM account WHERE Username = ? AND Password = ?";
        String query2 = "SELECT * FROM customer WHERE AccountId = ?";
        String query3 = "SELECT * FROM fullname WHERE Id = ?";
        String query4 = "SELECT * FROM address WHERE Id = ?";

        PreparedStatement[] ps = new PreparedStatement[4];
        ResultSet[] rs = new ResultSet[4];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);

        try {
            int accountId, fullnameId, addressId;
            ps[0] = connection.prepareStatement(query1);
            ps[0].setString(1, account.getUsername());
            ps[0].setString(2, account.getPassword());
            rs[0] = ps[0].executeQuery();

            if (rs[0].next()) {
                accountId = rs[0].getInt("Id");
                account.setId(accountId);
            } else {
                return null;
            }

            ps[1] = connection.prepareStatement(query2);
            ps[1].setInt(1, accountId);
            rs[1] = ps[1].executeQuery();

            if (rs[1].next()) {
                int customerId = rs[1].getInt("Id");
                fullnameId = rs[1].getInt("FullNameId");
                addressId = rs[1].getInt("AddressId");
                String tel = rs[1].getString("Tel");
                Date doB = rs[1].getDate("DoB");
                String sex = rs[1].getString("Sex");

                FullName fullName = new FullName();
                Address address = new Address();
                ps[2] = connection.prepareStatement(query3);
                ps[2].setInt(1, fullnameId);
                rs[2] = ps[2].executeQuery();
                rs[2].next();
                String firstName = rs[2].getString("FirstName");
                String midName = rs[2].getString("MidName");
                String lastName = rs[2].getString("LastName");
                fullName = new FullName(fullnameId, firstName, midName, lastName);

                ps[3] = connection.prepareStatement(query4);
                ps[3].setInt(1, addressId);
                rs[3] = ps[3].executeQuery();
                rs[3].next();
                int houseNo = rs[3].getInt("HouseNo");
                String street = rs[3].getString("Street");
                String district = rs[3].getString("District");
                String city = rs[3].getString("City");
                address = new Address(addressId, houseNo, street, district, city);

                Customer customer = new Customer(customerId, account, fullName, address, tel, doB, sex);
                return customer;
            }

            return null;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            for (int i = 0; i < ps.length; i++) {
                DBUtil.closePreparedStatement(ps[i]);
                DBUtil.closeResultSet(rs[i]);
            }
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public Customer addCustomer(Customer cus) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query0 = "INSERT INTO account (Username, Password) VALUES (?,?)";
        String query1 = "INSERT INTO fullname (FirstName, MidName, LastName) VALUES (?,?,?)";
        String query2 = "INSERT INTO address (HouseNo, Street, District, City) VALUES (?,?,?,?)";
        String query3 = "INSERT INTO customer (AccountId, FullNameId, AddressId, Tel, DoB, Sex) VALUES (?,?,?,?,?,?)";
        String query4 = "SELECT MAX(Id) AS LatestAccount FROM account";
        String query5 = "SELECT MAX(Id) AS LatestFullName FROM fullname";
        String query6 = "SELECT MAX(Id) AS LatestAddress FROM address";
        String query7 = "SELECT MAX(Id) AS LatestCustomer FROM customer";

        PreparedStatement[] ps = new PreparedStatement[8];
        ResultSet[] rs = new ResultSet[4];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);

        try {
            int customerId, accountId, fullnameId, addressId;
            ps[0] = connection.prepareStatement(query0);
            ps[0].setString(1, cus.getAccount().getUsername());
            ps[0].setString(2, cus.getAccount().getPassword());
            ps[0].executeUpdate();

            ps[1] = connection.prepareStatement(query4);
            rs[0] = ps[1].executeQuery();
            rs[0].next();
            accountId = rs[0].getInt("LatestAccount");

            ps[2] = connection.prepareStatement(query1);
            ps[2].setString(1, cus.getFullName().getFirstName());
            ps[2].setString(2, cus.getFullName().getMidName());
            ps[2].setString(3, cus.getFullName().getLastName());
            ps[2].executeUpdate();

            ps[3] = connection.prepareStatement(query5);
            rs[1] = ps[3].executeQuery();
            rs[1].next();
            fullnameId = rs[1].getInt("LatestFullName");

            ps[4] = connection.prepareStatement(query2);
            ps[4].setInt(1, cus.getAddress().getHouseNo());
            ps[4].setString(2, cus.getAddress().getStreet());
            ps[4].setString(3, cus.getAddress().getDistrict());
            ps[4].setString(4, cus.getAddress().getCity());
            ps[4].executeUpdate();

            ps[5] = connection.prepareStatement(query6);
            rs[2] = ps[5].executeQuery();
            rs[2].next();
            addressId = rs[2].getInt("LatestAddress");

            ps[6] = connection.prepareStatement(query3);
            ps[6].setInt(1, accountId);
            ps[6].setInt(2, fullnameId);
            ps[6].setInt(3, addressId);
            ps[6].setString(4, cus.getTel());
            ps[6].setDate(5, cus.getDoB());
            ps[6].setString(6, cus.getSex());
            ps[6].executeUpdate();

            ps[7] = connection.prepareStatement(query7);
            rs[3] = ps[7].executeQuery();
            rs[3].next();
            customerId = rs[3].getInt("LatestCustomer");

            cus.setId(customerId);
            cus.getAccount().setId(accountId);
            cus.getFullName().setId(fullnameId);
            cus.getAddress().setId(addressId);

            return cus;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            for (int i = 0; i < ps.length; i++) {
                DBUtil.closePreparedStatement(ps[i]);
            }
            for (int i = 0; i < rs.length; i++) {
                DBUtil.closeResultSet(rs[i]);
            }
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query1 = "SELECT * FROM customer WHERE Id = ?";
        String query2 = "SELECT * FROM account WHERE Id = ?";
        String query3 = "SELECT * FROM fullname WHERE Id = ?";
        String query4 = "SELECT * FROM address WHERE Id = ?";

        PreparedStatement[] ps = new PreparedStatement[4];
        ResultSet[] rs = new ResultSet[4];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);

        try {
            ps[0] = connection.prepareStatement(query1);
            ps[0].setInt(1, customerId);
            rs[0] = ps[0].executeQuery();
            rs[0].next();

            int accountId = rs[0].getInt("AccountId");
            int fullNameId = rs[0].getInt("FullNameId");
            int addressId = rs[0].getInt("AddressId");
            String tel = rs[0].getString("Tel");
            Date doB = rs[0].getDate("DoB");
            String sex = rs[0].getString("Sex");

            ps[1] = connection.prepareStatement(query2);
            ps[1].setInt(1, accountId);
            rs[1] = ps[1].executeQuery();
            rs[1].next();
            String username = rs[1].getString("Username");
            String password = rs[1].getString("Password");
            Account account = new Account(accountId, username, password);

            ps[2] = connection.prepareStatement(query3);
            ps[2].setInt(1, fullNameId);
            rs[2] = ps[2].executeQuery();
            rs[2].next();
            String firstName = rs[2].getString("FirstName");
            String midName = rs[2].getString("MidName");
            String lastName = rs[2].getString("LastName");
            FullName fullName = new FullName(fullNameId, firstName, midName, lastName);

            ps[3] = connection.prepareStatement(query4);
            ps[3].setInt(1, addressId);
            rs[3] = ps[3].executeQuery();
            rs[3].next();
            int houseNo = rs[3].getInt("HouseNo");
            String street = rs[3].getString("Street");
            String district = rs[3].getString("District");
            String city = rs[3].getString("City");
            Address address = new Address(addressId, houseNo, street, district, city);

            Customer customer = new Customer(customerId, account, fullName, address, tel, doB, sex);
            return customer;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            for (int i = 0; i < ps.length; i++) {
                DBUtil.closePreparedStatement(ps[i]);
                DBUtil.closeResultSet(rs[i]);
            }
            connectionPool.freeConnection(connection);
        }
    }

}
