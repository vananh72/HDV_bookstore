/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.customerDAO;

import model.customer.Account;
import model.customer.Customer;

/**
 *
 * @author pc
 */
public interface CustomerDAO {
    Customer checkLogin(Account account);
    Customer addCustomer(Customer cus);
    Customer getCustomerById(int customerId);
}
