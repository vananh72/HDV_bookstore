/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.itemBookDAO;

import java.util.List;
import model.book.Book;
import model.itemBook.ItemBook;

/**
 *
 * @author pc
 */
public interface ItemBookDAO {
    ItemBook getItemBookByCode(String code);
    List<ItemBook> searchByTitle(String title);
    List<ItemBook> getAllItemBook();
    Book getBook(String isbn);
    List<Book> getBookByTitle(String title);
    ItemBook addItemBook(ItemBook itemBook);
    boolean deleteItemBook(ItemBook itemBook);
    ItemBook modifyItemBook(ItemBook itemBook);
}
