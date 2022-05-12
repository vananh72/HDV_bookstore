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
import logicApplication.itemBookDAO.ItemBookDAOImpl;
import model.itemBook.ItemBook;

/**
 *
 * @author pc
 */
@WebServlet(name = "CustomerSearchServlet", urlPatterns = {"/search"})
public class CustomerSearchServlet extends HttpServlet {
    private ItemBookDAOImpl itemBookDAOImpl;

    public CustomerSearchServlet() {
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
        searchSubmit(request, response);
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
        searchSubmit(request, response);
    }

    private void searchSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String bookTitle = request.getParameter("bookTitle");

        System.out.println("Search for " + bookTitle);
        String searchReply;
        List<ItemBook> itemBooks = itemBookDAOImpl.searchByTitle(bookTitle);
        if (itemBooks.isEmpty()) {
            searchReply = "No result";
            request.setAttribute("bookTitle", bookTitle);
            request.setAttribute("searchReply", searchReply);
            System.out.println(searchReply);

            RequestDispatcher dispatcher = request.getRequestDispatcher("SearchResultCustomer.jsp");
            dispatcher.forward(request, response);
        } else {
            if(request.getParameter("reply") != null && request.getParameter("barcode") != null){
                request.setAttribute("reply", request.getParameter("reply"));
                request.setAttribute("barcode", request.getParameter("barcode"));
            }
            request.setAttribute("listItemBooks", itemBooks);
            request.setAttribute("bookTitle", bookTitle);
            System.out.println("Found");

            RequestDispatcher dispatcher = request.getRequestDispatcher("SearchResultCustomer.jsp");
            dispatcher.forward(request, response);
        }
    }
}
