/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicApplication.cartDAO.CartDAOImpl;
import logicApplication.itemBookDAO.ItemBookDAOImpl;
import model.cart.Cart;
import model.itemBook.ItemBook;

/**
 *
 * @author pc
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/addToCart"})
public class AddToCartServlet extends HttpServlet {
    private CartDAOImpl cartDAOImpl;
    private ItemBookDAOImpl itemBookDAOImpl;

    public AddToCartServlet() {
        cartDAOImpl = new CartDAOImpl();
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
        addToCart(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemBarcode = request.getParameter("itemBarcode");
        String quantity = request.getParameter("quantity");
        int cartId;

        if (request.getParameter("cartId").isEmpty()) {
            Cart cart = cartDAOImpl.createCart();
            System.out.println(cart.getId());
            Cookie cartCookie = new Cookie("cartCookie", Integer.toString(cart.getId()));
            cartCookie.setMaxAge(60 * 60 * 2);
            response.addCookie(cartCookie);
            cartId = cart.getId();
        } else {
            cartId = Integer.parseInt(request.getParameter("cartId"));
        }

        String reply;
        System.out.println("Them san pham " + itemBarcode + " vao gio hang co ma " + cartId);
        ItemBook itemBook = itemBookDAOImpl.getItemBookByCode(itemBarcode);
        Cart newCart = cartDAOImpl.getCartById(cartId);

        try {
            int quan = Integer.parseInt(quantity);
            if (quan <= 0) {
                System.out.println("So luong khong hop le");
                reply = "Invalid";
            } else {
                if (cartDAOImpl.addItemBookToCart(itemBook, newCart, quan)) {
                    System.out.println("Them thanh cong");
                    newCart = cartDAOImpl.getCartById(cartId);

                    reply = "Success";
                } else {
                    System.out.println("Them khong thanh cong");
                    reply = "Failed";
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("So luong khong hop le");
            reply = "Invalid";
        }

        Cookie replyCookie = new Cookie("replyCookie", reply);
        Cookie barcodeCookie = new Cookie("barcodeCookie", itemBarcode);
        replyCookie.setMaxAge(1);
        barcodeCookie.setMaxAge(1);
        response.addCookie(replyCookie);
        response.addCookie(barcodeCookie);

        response.sendRedirect("homeCus");
    }

}
