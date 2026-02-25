package com.sourav.shoppingcart.repository.cartItemReository;

import com.sourav.shoppingcart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem ,Long> {
    void deleteAllByCartId(Long id);
}
