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
import model.cart.Cart;

/**
 *
 * @author pc
 */
@WebServlet(name = "ShowCartController", urlPatterns = {"/cart"})
public class ShowCartServlet extends HttpServlet {
    private CartDAOImpl cartDAOImpl;

    public ShowCartServlet() {
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
        showCart(request, response);
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
        showCart(request, response);
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String cartId = "";
        
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("cartCookie")){
                    cartId = cookie.getValue();
                }
            }
        }
        System.out.println("Lay thong tin cho gio hang " + cartId);
        
        if(cartId != ""){
            Cart cart = cartDAOImpl.getCartById(Integer.parseInt(cartId));
            request.setAttribute("cartAvailable", "Yes");
            request.setAttribute("cart", cart);
        } else{
            request.setAttribute("cartAvailable", "No");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
        dispatcher.forward(request, response);
    }
}
