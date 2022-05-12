/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.itemBookDAO;

import connectionPool.ConnectionPool;
import connectionPool.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicApplication.bookDAO.BookDAOImpl;
import model.book.Book;
import model.itemBook.Image;
import model.itemBook.ItemBook;

/**
 *
 * @author pc
 */
public class ItemBookDAOImpl implements ItemBookDAO {

    @Override
    public ItemBook getItemBookByCode(String code) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query1 = "SELECT * FROM itembook WHERE Barcode = ?";
        String query2 = "SELECT * FROM image WHERE ItemBookBarcode = ?";
        PreparedStatement[] ps = new PreparedStatement[2];
        ResultSet[] rs = new ResultSet[2];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);

        try {
            ps[0] = connection.prepareStatement(query1);
            ps[0].setString(1, code);
            rs[0] = ps[0].executeQuery();

            ItemBook itemBook = null;

            rs[0].next();
            String isbn = rs[0].getString("BookISBN");
            float price = rs[0].getFloat("Price");
            float discount = rs[0].getFloat("Discount");
            String promoText = rs[0].getString("PromoText");
            String description = rs[0].getString("Description");

            List<Image> images = new ArrayList<>();
            ps[1] = connection.prepareStatement(query2);
            ps[1].setString(1, code);
            rs[1] = ps[1].executeQuery();
            while (rs[1].next()) {
                int id = rs[1].getInt("Id");
                String src = rs[1].getString("Src");
                images.add(new Image(id, src));
            }

            Book book = getBook(isbn);
            itemBook = new ItemBook(code, price, discount, promoText, description, book, images);
            return itemBook;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            for (int i = 0; i < ps.length; i++) {
                DBUtil.closePreparedStatement(ps[i]);
                DBUtil.closeResultSet(rs[i]);
            }
            pool.freeConnection(connection);
        }
    }

    @Override
    public List<ItemBook> searchByTitle(String title) {
        List<Book> listBooks = getBookByTitle(title);

        if (listBooks == null) {
            return null;
        } else {
            List<ItemBook> listItemBooks = new ArrayList<>();
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            String query1 = "SELECT * FROM itembook WHERE BookISBN = ?";
            String query2 = "SELECT * FROM image WHERE ItemBookBarcode = ?";
            PreparedStatement[] ps = new PreparedStatement[2];
            ResultSet[] rs = new ResultSet[2];

            try {
                ps[0] = connection.prepareStatement(query1);
                for (Book book : listBooks) {
                    ps[0].setString(1, book.getIsbn());
                    rs[0] = ps[0].executeQuery();

                    while (rs[0].next()) {
                        String barcode = rs[0].getString("Barcode");
                        float price = rs[0].getFloat("Price");
                        float discount = rs[0].getFloat("Discount");
                        String promoText = rs[0].getString("PromoText");
                        String description = rs[0].getString("Description");

                        List<Image> images = new ArrayList<>();
                        ps[1] = connection.prepareStatement(query2);
                        ps[1].setString(1, barcode);
                        rs[1] = ps[1].executeQuery();
                        while (rs[1].next()) {
                            int id = rs[1].getInt("Id");
                            String src = rs[1].getString("Src");
                            images.add(new Image(id, src));
                        }

                        listItemBooks.add(new ItemBook(barcode, price, discount, promoText, description, book, images));
                    }
                }

                return listItemBooks;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            } finally {
                for (int i = 0; i < ps.length; i++) {
                    DBUtil.closePreparedStatement(ps[i]);
                    DBUtil.closeResultSet(rs[i]);
                }
                pool.freeConnection(connection);
            }
        }
    }

    @Override
    public List<ItemBook> getAllItemBook() {
        List<ItemBook> listItemBooks = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query1 = "SELECT * FROM itembook";
        String query2 = "SELECT * FROM image WHERE ItemBookBarcode = ?";

        PreparedStatement[] ps = new PreparedStatement[2];
        ResultSet[] rs = new ResultSet[2];

        try {
            ps[0] = connection.prepareStatement(query1);
            rs[0] = ps[0].executeQuery();

            while (rs[0].next()) {
                String barcode = rs[0].getString("Barcode");
                String bookISBN = rs[0].getString("BookISBN");
                float price = rs[0].getFloat("Price");
                float discount = rs[0].getFloat("Discount");
                String promoText = rs[0].getString("PromoText");
                String description = rs[0].getString("Description");

                List<Image> images = new ArrayList<>();
                ps[1] = connection.prepareStatement(query2);
                ps[1].setString(1, barcode);
                rs[1] = ps[1].executeQuery();
                while (rs[1].next()) {
                    int id = rs[1].getInt("Id");
                    String src = rs[1].getString("Src");
                    images.add(new Image(id, src));
                }

                Book book = getBook(bookISBN);
                listItemBooks.add(new ItemBook(barcode, price, discount, promoText, description, book, images));
            }
            return listItemBooks;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            for(int i = 0; i < 2; i++){
                DBUtil.closePreparedStatement(ps[i]);
                DBUtil.closeResultSet(rs[i]);
            }
            pool.freeConnection(connection);
        }
    }

    @Override
    public Book getBook(String isbn) {
        BookDAOImpl bookDAOImpl = new BookDAOImpl();
        return bookDAOImpl.getByISBN(isbn);
    }

    @Override
    public List<Book> getBookByTitle(String title) {
        BookDAOImpl bookDAOImpl = new BookDAOImpl();
        return bookDAOImpl.getByTitle(title);
    }

    @Override
    public ItemBook addItemBook(ItemBook itemBook) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        
        String query1 = "INSERT INTO itembook (Barcode, BookISBN, Price, Discount, PromoText, Description) VALUES (?,?,?,?,?,?)";
        String query2 = "INSERT INTO image (ItemBookBarcode, Src) VALUES (?,?,?)";
        String query3 = "SELECT MAX(Id) AS LatestImage FROM image";
        
        PreparedStatement[] ps = new PreparedStatement[3];
        ResultSet[] rs = new ResultSet[3];
        
        try {
            ps[0] = connection.prepareStatement(query1);
            ps[0].setString(1, itemBook.getBarcode());
            ps[0].setString(2, itemBook.getBook().getIsbn());
            ps[0].setFloat(3, itemBook.getPrice());
            ps[0].setFloat(4, itemBook.getDiscount());
            ps[0].setString(5, itemBook.getPromoText());
            ps[0].setString(6, itemBook.getDescription());
            ps[0].executeUpdate();
            
            List<Image> updatedImages = new ArrayList<>();
            for(Image image: itemBook.getImages()){
                ps[2] = connection.prepareStatement(query2);
                ps[2].setString(1, itemBook.getBarcode());
                ps[2].setString(2, image.getSrc());
                ps[2].executeUpdate();
                
                Image tmpImage = image;
                ps[3] = connection.prepareStatement(query3);
                rs[3] = ps[3].executeQuery();
                rs[3].next();
                int imageId = rs[3].getInt("LatestImage");
                tmpImage.setId(imageId);
                updatedImages.add(tmpImage);
            }
            itemBook.setImages(updatedImages);
            
            return itemBook;
        } catch (SQLException ex) {
            Logger.getLogger(ItemBookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            for(int i = 0; i < ps.length; i++){
                DBUtil.closePreparedStatement(ps[i]);
                DBUtil.closeResultSet(rs[i]);
            }
            connectionPool.freeConnection(connection);
        }
    }

    @Override
    public boolean deleteItemBook(ItemBook itemBook) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemBook modifyItemBook(ItemBook itemBook) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
