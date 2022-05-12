/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicApplication.cartDAO.CartDAOImpl;
import logicApplication.customerDAO.CustomerDAOImpl;
import logicApplication.orderDAO.OrderDAOImpl;
import logicApplication.orderDAO.PaymentDAOImpl;
import logicApplication.orderDAO.ShipmentDAOImpl;
import model.cart.Cart;
import model.customer.Customer;
import model.order.Credit;
import model.order.Order;
import model.order.Payment;
import model.order.Shipment;

/**
 *
 * @author pc
 */
@WebServlet(name = "ShipmentServlet", urlPatterns = {"/shipment"})
public class ShipmentServlet extends HttpServlet {
    private CustomerDAOImpl customerDAOImpl;
    private OrderDAOImpl orderDAOImpl;
    private ShipmentDAOImpl shipmentDAOImpl;
    private PaymentDAOImpl paymentDAOImpl;
    private CartDAOImpl cartDAOImpl;

    public ShipmentServlet() {
        customerDAOImpl = new CustomerDAOImpl();
        orderDAOImpl = new OrderDAOImpl();
        shipmentDAOImpl = new ShipmentDAOImpl();
        paymentDAOImpl = new PaymentDAOImpl();
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
        shipmentSubmit(request, response);
    }

    private void shipmentSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        Customer customer = customerDAOImpl.getCustomerById(Integer.parseInt(customerId));

        request.setCharacterEncoding("UTF-8");
        String paymentId = request.getParameter("paymentId");
        Payment payment = paymentDAOImpl.getPaymentById(Integer.parseInt(paymentId));

        String shippingAddress = request.getParameter("shippingAddress");
        Shipment shipment = new Shipment();
        shipment.setShippingAddress(shippingAddress);
        String method;
        float cost;
        String type = request.getParameter("method");
        if (type.equals("1")) {
            method = "Bưu điện Việt Nam (VNPost)";
            cost = 50000;
            shipment.setMethod(method);
            shipment.setCost(cost);
        } else if (type.equals("2")) {
            method = "Viettel Post";
            cost = 60000;
            shipment.setMethod(method);
            shipment.setCost(cost);
        } else if (type.equals("3")) {
            method = "DHL";
            cost = 55000;
            shipment.setMethod(method);
            shipment.setCost(cost);
        } else if (type.equals("4")) {
            method = "Giaohangnhanh";
            cost = 65000;
            shipment.setMethod(method);
            shipment.setCost(cost);
        }
        shipment.setPayment(payment);
        shipment = shipmentDAOImpl.addShipment(shipment);

        float totalPrice = cart.getTotalPrice() + shipment.getCost();
        float tax = 0;
        String status = "Đặt hàng thành công";
        Date orderDate = Date.valueOf(LocalDate.now());

        Order order = new Order();
        order.setCart(cart);
        order.setShipment(shipment);
        order.setPayment(payment);
        order.setCustomer(customer);
        order.setTotalPrice(totalPrice);
        order.setTax(tax);
        order.setStatus(status);
        order.setOrderDate(orderDate);

        order = orderDAOImpl.addOrder(order);
        if (payment.getMethod().equals("Credit")) {
            Credit credit = (Credit) payment;
            request.setAttribute("credit", credit);
        }
        request.setAttribute("order", order);
        Cart newCart = cartDAOImpl.createCart();
        Cookie cartCookie = new Cookie("cartCookie", Integer.toString(newCart.getId()));
        cartCookie.setMaxAge(60 * 60 * 2);
        response.addCookie(cartCookie);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Order.jsp");
        dispatcher.forward(request, response);
    }
}
