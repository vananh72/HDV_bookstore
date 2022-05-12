/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicApplication.customerDAO.CustomerDAOImpl;
import model.customer.Account;
import model.customer.Address;
import model.customer.Customer;
import model.customer.FullName;

/**
 *
 * @author pc
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private CustomerDAOImpl customerDAOImpl;

    public RegisterServlet() {
        customerDAOImpl = new CustomerDAOImpl();
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            default:
                showRegisterPage(request, response);
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        registerSubmit(request, response);
    }

    private void showRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
        dispatcher.forward(request, response);
    }

    private void registerSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        Account account = new Account();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        account.setUsername(username);
        account.setPassword(password);
        
        FullName fullName = new FullName();
        String lastName = request.getParameter("lastName");
        String midName = request.getParameter("midName");
        String firstName = request.getParameter("firstName");
        fullName.setFirstName(firstName);
        fullName.setMidName(midName);
        fullName.setLastName(lastName);
        
        Address address = new Address();
        String houseNo = request.getParameter("houseNo");
        String street = request.getParameter("street");
        String district = request.getParameter("district");
        String city = request.getParameter("city");
        address.setHouseNo(Integer.parseInt(houseNo));
        address.setStreet(street);
        address.setDistrict(district);
        address.setCity(city);
        
        Customer customer = new Customer();
        customer.setAccount(account);
        customer.setFullName(fullName);
        customer.setAddress(address);
        String tel = request.getParameter("tel");
        String doB = request.getParameter("doB");
        String sex = request.getParameter("sex");
        customer.setTel(tel);
        customer.setDoB(Date.valueOf(doB));
        customer.setSex(sex);
        
        String reply;
        customer = customerDAOImpl.addCustomer(customer);
        if(customer == null){
            reply = "Failed";
        } else {
            reply = "Success";
        }
        request.setAttribute("customer", customer);
        request.setAttribute("reply", reply);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
        dispatcher.forward(request, response);
    }
}
