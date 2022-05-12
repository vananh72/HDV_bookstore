/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.employeeDAO;

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
import model.employee.Account;
import model.employee.Address;
import model.employee.Employee;
import model.employee.FullName;

/**
 *
 * @author pc
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public Employee checkLogin(Account account) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query1 = "SELECT * FROM account WHERE Username = ? AND Password = ?";
        String query2 = "SELECT * FROM employee WHERE AccountId = ?";
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
                int employeeId = rs[1].getInt("Id");
                fullnameId = rs[1].getInt("FullNameId");
                addressId = rs[1].getInt("AddressId");
                String tel = rs[1].getString("Tel");
                Date doB = rs[1].getDate("DoB");
                String sex = rs[1].getString("Sex");
                String role = rs[1].getString("Role");

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

                Employee employee = new Employee(employeeId, account, address, fullName, tel, doB, sex, role);
                return employee;
            }

            return null;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
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
