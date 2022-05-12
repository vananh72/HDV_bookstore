/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.bookDAO;

import java.util.List;
import model.book.Author;
import model.book.Book;
import model.book.Category;
import model.book.Publisher;

/**
 *
 * @author pc
 */
public interface BookDAO {
    Book getByISBN(String isbn);
    List<Book> getByTitle(String title);
    Book addBook(Book book);
    boolean modifyBookInfo(Book book);
    boolean deleteBook(Book book);
}
