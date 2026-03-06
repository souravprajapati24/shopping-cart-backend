package com.sourav.shoppingcart.service.order;


import com.sourav.shoppingcart.dto.OrderDto;
import com.sourav.shoppingcart.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
