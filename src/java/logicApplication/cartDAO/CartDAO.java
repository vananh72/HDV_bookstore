/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicApplication.cartDAO;

import java.util.List;
import model.cart.Cart;
import model.cart.SelectedItems;
import model.itemBook.ItemBook;
import model.order.Payment;

/**
 *
 * @author pc
 */
public interface CartDAO {
    Cart createCart();
    Cart getCartById(int id);
    boolean addItemBookToCart(ItemBook itemBook, Cart cart, int quantity);
    boolean removeItemBookFromCart(Cart cart, SelectedItems selectedItem);
    boolean changeQuantity(Cart cart, SelectedItems selectedItem);
    List<SelectedItems> getItemsOfCart(int cartId);
    Payment getPayment(Payment payment);
}
