/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.bookDAO;

import connectionPool.ConnectionPool;
import connectionPool.DBUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.book.Author;
import model.book.Book;
import model.book.Category;
import model.book.Publisher;

/**
 *
 * @author pc
 */
public class BookDAOImpl implements BookDAO {

    @Override
    public Book getByISBN(String isbn) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Book book = new Book();

        String query1 = "SELECT * FROM book WHERE ISBN = ?";
        String query2 = "SELECT * FROM publisher WHERE ID = ?";
        String query3 = "SELECT * FROM category WHERE ID = ?";
        String query4 = "SELECT AuthorID FROM book_author WHERE BookISBN = ?";
        String query5 = "SELECT * FROM author WHERE ID = ?";

        PreparedStatement[] ps = new PreparedStatement[5];
        ResultSet[] rs = new ResultSet[5];
        Arrays.fill(ps, null);
        Arrays.fill(rs, null);

        try {
            ps[0] = connection.prepareStatement(query1);
            ps[0].setString(1, isbn);
            rs[0] = ps[0].executeQuery();

            if (rs[0].next()) {
                // Lay cac tham so co ban cua Book
                int categoryId = rs[0].getInt("CategoryID");
                int publisherId = rs[0].getInt("PublisherID");
                String title = rs[0].getString("Title");
                String summary = rs[0].getString("Summary");
                Date publicationDate = rs[0].getDate("PublicationDate");
                int numOfPages = rs[0].getInt("NumOfPages");
                String language = rs[0].getString("Language");
                String dimensions = rs[0].getString("Dimensions");
                float weight = rs[0].getFloat("Weight");
                String edition = rs[0].getString("Edition");

                book.setIsbn(isbn);
                book.setTitle(title);
                book.setSummary(summary);
                book.setPublicationDate(publicationDate);
                book.setNumOfPages(numOfPages);
                book.setLanguage(language);
                book.setDimensions(dimensions);
                book.setWeight(weight);
                book.setEdition(edition);

                Publisher publisher = null;
                Category category = null;
                List<Integer> authorIDs = new ArrayList<>();
                List<Author> authors = new ArrayList<>();

                // Lay Publisher
                ps[1] = connection.prepareStatement(query2);
                ps[1].setInt(1, publisherId);
                rs[1] = ps[1].executeQuery();

                if (rs[1].next()) {
                    String name = rs[1].getString("Name");
                    String address = rs[1].getString("Address");
                    publisher = new Publisher(publisherId, name, address);
                    book.setPublisher(publisher);
                }

                // Lay Category
                ps[2] = connection.prepareStatement(query3);
                ps[2].setInt(1, categoryId);
                rs[2] = ps[2].executeQuery();

                if (rs[2].next()) {
                    String type = rs[2].getString("Type");
                    category = new Category(categoryId, type);
                    book.setCategory(category);
                }

                // Lay danh sach ID cac tac gia
                ps[3] = connection.prepareStatement(query4);
                ps[3].setString(1, isbn);
                rs[3] = ps[3].executeQuery();

                while (rs[3].next()) {
                    int authorID = rs[3].getInt("AuthorID");
                    authorIDs.add(authorID);
                }

                // Lay List<Author>
                ps[4] = connection.prepareStatement(query5);
                for (Integer authorID : authorIDs) {
                    Author author = null;
                    ps[4].setInt(1, authorID);
                    rs[4] = ps[4].executeQuery();

                    if (rs[4].next()) {
                        String name = rs[4].getString("Name");
                        String bio = rs[4].getString("Bio");
                        author = new Author(authorID, name, bio);
                        authors.add(author);
                    }
                }
                book.setAuthor(authors);

                return book;
            } else {
                return null;
            }
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
    public List<Book> getByTitle(String title) {
        List<Book> resBooks = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        String query1 = "SELECT * FROM book WHERE Title LIKE ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = connection.prepareStatement(query1);
            StringBuilder builder = new StringBuilder("%");
            builder.append(title);
            builder.append("%");
            ps.setString(1, builder.toString()); // SELECT * FROM book WHERE Title LIKE %title%
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                Book book = getByISBN(isbn);
                resBooks.add(book);
            }
            return resBooks;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    @Override
    public Book addBook(Book book) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();

        String query1 = "INSERT INTO publisher (Name, Address) VALUES (?,?)";
        String query2 = "INSERT INTO author (Name, Bio) VALUES (?,?)";
        String query3 = "INSERT INTO category (Type) VALUES (?)";
        String query4 = "SELECT MAX(Id) AS LatestPublisher FROM publisher";
        String query5 = "SELECT MAX(Id) AS LatestAuthor FROM author";
        String query6 = "SELECT MAX(Id) AS LatestCategory FROM category";
        String query7 = "INSERT INTO book VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        String query8 = "INSERT INTO book_author (BookISBN, AuthorId) VALUES (?,?)";

        PreparedStatement[] ps = new PreparedStatement[8];
        ResultSet[] rs = new ResultSet[8];

        try {
            // Update for Publisher
            ps[0] = connection.prepareStatement(query1);
            ps[0].setString(1, book.getPublisher().getName());
            ps[0].setString(2, book.getPublisher().getAddress());
            ps[0].executeUpdate();

            ps[1] = connection.prepareCall(query4);
            rs[1] = ps[1].executeQuery();
            rs[1].next();
            int publisherId = rs[1].getInt("LatestPublisher");
            book.getPublisher().setId(publisherId);

            // Update for Author
            List<Author> updatedAuthors = new ArrayList<>();
            for (Author author : book.getAuthor()) {
                ps[2] = connection.prepareStatement(query2);
                ps[2].setString(1, author.getName());
                ps[2].setString(2, author.getBiography());
                ps[2].executeUpdate();

                Author tmpAuthor = author;
                ps[3] = connection.prepareStatement(query5);
                rs[3] = ps[3].executeQuery();
                rs[3].next();
                int authorId = rs[3].getInt("LatestAuthor");
                tmpAuthor.setId(authorId);
                updatedAuthors.add(tmpAuthor);
            }
            book.setAuthor(updatedAuthors);
            
            // Update for Category
            ps[4] = connection.prepareStatement(query3);
            ps[4].setString(1, book.getCategory().getType());
            ps[4].executeUpdate();
            
            ps[5] = connection.prepareStatement(query6);
            rs[5] = ps[5].executeQuery();
            rs[5].next();
            int categoryId = rs[5].getInt("LatestCategory");
            book.getCategory().setId(categoryId);
            
            // Insert Book
            ps[6] = connection.prepareStatement(query7);
            ps[6].setString(1, book.getIsbn());
            ps[6].setInt(2, categoryId);
            ps[6].setInt(3, publisherId);
            ps[6].setString(4, book.getTitle());
            ps[6].setString(5, book.getSummary());
            ps[6].setDate(6, book.getPublicationDate());
            ps[6].setInt(7, book.getNumOfPages());
            ps[6].setString(8, book.getLanguage());
            ps[6].setString(9, book.getDimensions());
            ps[6].setFloat(10, book.getWeight());
            ps[6].setString(11, book.getEdition());
            ps[6].executeUpdate();
            
            // Update for Book_Author
            for(Author author: updatedAuthors){
                ps[7] = connection.prepareStatement(query8);
                ps[7].setString(1, book.getIsbn());
                ps[7].setInt(2, author.getId());
                ps[7].executeUpdate();
            }
            
            return book;
        } catch (SQLException ex) {
            Logger.getLogger(BookDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean modifyBookInfo(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteBook(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
