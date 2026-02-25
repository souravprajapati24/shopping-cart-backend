package com.sourav.shoppingcart.service.cart;

import com.sourav.shoppingcart.model.Cart;
import com.sourav.shoppingcart.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId , Long productId , int quantity);
    void removeItemFromCart(Long cartId , Long productId);
    void updateItemQuantity(Long cartId , Long productId , int quantity);


    CartItem getCartItem(Cart cart, Long productId);
}
