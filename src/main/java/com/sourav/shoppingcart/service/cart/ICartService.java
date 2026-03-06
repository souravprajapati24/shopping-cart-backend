package com.sourav.shoppingcart.service.cart;

import com.sourav.shoppingcart.model.Cart;
import com.sourav.shoppingcart.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);

    Cart initializeNewCart(User user);

    Cart getCartByUserID(Long userId);
}
