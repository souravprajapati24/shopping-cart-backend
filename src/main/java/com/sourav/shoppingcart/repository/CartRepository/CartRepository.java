package com.sourav.shoppingcart.repository.CartRepository;

import com.sourav.shoppingcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart , Long> {
}
