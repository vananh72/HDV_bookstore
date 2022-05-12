/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicApplication.cartDAO.CartDAOImpl;
import logicApplication.customerDAO.CustomerDAOImpl;
import logicApplication.employeeDAO.EmployeeDAOImpl;
import logicApplication.itemBookDAO.ItemBookDAOImpl;
import model.cart.Cart;
import model.customer.Account;
import model.customer.Customer;
import model.employee.Employee;
import model.itemBook.ItemBook;

/**
 *
 * @author pc
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private CustomerDAOImpl customerDAOImpl;
    private EmployeeDAOImpl employeeDAOImpl;
    private ItemBookDAOImpl itemBookDAOImpl;
    
    public LoginServlet() {
        customerDAOImpl = new CustomerDAOImpl();
        employeeDAOImpl = new EmployeeDAOImpl();
        itemBookDAOImpl = new ItemBookDAOImpl();
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
        showLoginPage(request, response);
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
        loginSubmit(request, response);
    }

    private void showLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
        dispatcher.forward(request, response);
    }

    private void loginSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Account account = new Account();
        model.employee.Account accEmployee = new model.employee.Account();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        account.setUsername(username);
        account.setPassword(password);
        accEmployee.setUsername(username);
        accEmployee.setPassword(password);

        Customer customer = new Customer();
        customer = customerDAOImpl.checkLogin(account);
        Employee employee = new Employee();
        employee = employeeDAOImpl.checkLogin(accEmployee);
        String reply;

        if (customer == null && employee == null) {
            reply = "Failed";

            request.setAttribute("reply", reply);

            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
        } else {
            reply = "Success";
            List<ItemBook> itemBooks = itemBookDAOImpl.getAllItemBook();

            if (customer != null) {
                Cookie customerIdCookie = new Cookie("customerIdCookie", Integer.toString(customer.getId()));
                customerIdCookie.setMaxAge(-1);
                response.addCookie(customerIdCookie);

                Cookie usernameCookie = new Cookie("usernameCookie", customer.getAccount().getUsername());
                usernameCookie.setMaxAge(-1);
                response.addCookie(usernameCookie);
                
                request.setAttribute("listItemBooks", itemBooks);
                request.setAttribute("username", customer.getAccount().getUsername());
                RequestDispatcher dispatcher = request.getRequestDispatcher("HomePageCustomer.jsp");
                dispatcher.forward(request, response);
            }

            if (employee != null) {
                Cookie employeeIdCookie = new Cookie("employeeIdCookie", Integer.toString(employee.getId()));
                employeeIdCookie.setMaxAge(-1);
                response.addCookie(employeeIdCookie);

                Cookie usernameCookie = new Cookie("usernameCookie", employee.getAccount().getUsername());
                usernameCookie.setMaxAge(-1);
                response.addCookie(usernameCookie);

                Cookie roleCookie = new Cookie("roleCookie", employee.getRole());
                roleCookie.setMaxAge(-1);
                response.addCookie(roleCookie);

                request.setAttribute("listItemBooks", itemBooks);
                request.setAttribute("username", employee.getAccount().getUsername());
                request.setAttribute("role", employee.getRole());
                RequestDispatcher dispatcher = request.getRequestDispatcher("HomePageEmployee.jsp");
                dispatcher.forward(request, response);
            }
        }

    }

}
