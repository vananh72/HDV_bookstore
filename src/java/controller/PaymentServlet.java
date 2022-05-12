/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicApplication.cartDAO.CartDAOImpl;
import logicApplication.customerDAO.CustomerDAOImpl;
import logicApplication.orderDAO.PaymentDAOImpl;
import model.cart.Cart;
import model.customer.Customer;
import model.order.Cash;
import model.order.Credit;
import model.order.Payment;

/**
 *
 * @author pc
 */
@WebServlet(name = "PaymentServlet", urlPatterns = {"/payment"})
public class PaymentServlet extends HttpServlet {
    private PaymentDAOImpl paymentDAOImpl;
    private CustomerDAOImpl customerDAOImpl;
    private CartDAOImpl cartDAOImpl;

    public PaymentServlet() {
        paymentDAOImpl = new PaymentDAOImpl();
        customerDAOImpl = new CustomerDAOImpl();
        cartDAOImpl = new CartDAOImpl();
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
        showPaymentPage(request, response);
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
        paymentSubmit(request, response);
    }

    private void showPaymentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("PaymentInfo.jsp");
        dispatcher.forward(request, response);
    }

    private void paymentSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Cookie[] cookies = request.getCookies();
        String cartId = "";
        String customerId = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cartCookie")) {
                    cartId = cookie.getValue();
                }
                if (cookie.getName().equals("customerIdCookie")) {
                    customerId = cookie.getValue();
                }
            }
        }

        Cart cart = cartDAOImpl.getCartById(Integer.parseInt(cartId));

        String method = request.getParameter("method");
        System.out.println(method);
        if (method.equals("Cash")) {
            Payment payment = cart.getPayment();
            int paymentId = payment.getId();
            Cash cash = new Cash(cart.getTotalPrice(), paymentId, method);
            paymentDAOImpl.addPayment((Payment) cash);

            request.setAttribute("paymentId", cart.getPayment().getId());
            Customer customer = customerDAOImpl.getCustomerById(Integer.parseInt(customerId));
            request.setAttribute("customer", customer);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ShipmentInfo.jsp");
            dispatcher.forward(request, response);
        } else if (method.equals("Credit")) {
            Payment payment = cart.getPayment();
            int paymentId = payment.getId();

            if (request.getParameter("cardId").isEmpty() || request.getParameter("accountId").isEmpty()
                    || request.getParameter("bank").isEmpty() || request.getParameter("type").isEmpty() || request.getParameter("expDate").isEmpty()) {
                String reply = "Failed";
                request.setAttribute("reply", reply);

                RequestDispatcher dispatcher = request.getRequestDispatcher("PaymentInfo.jsp");
                dispatcher.forward(request, response);
            } else {
                String cardId = request.getParameter("cardId");
                String accountId = request.getParameter("accountId");
                String bank = request.getParameter("bank");
                String type = request.getParameter("type");
                Date expDate = Date.valueOf(request.getParameter("expDate"));
                Credit credit = new Credit(cardId, accountId, bank, type, expDate, paymentId, method);
                paymentDAOImpl.addPayment((Payment) credit);

                request.setAttribute("paymentId", cart.getPayment().getId());
                Customer customer = customerDAOImpl.getCustomerById(Integer.parseInt(customerId));
                request.setAttribute("customer", customer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("ShipmentInfo.jsp");
                dispatcher.forward(request, response);
            }
        }

    }

}
