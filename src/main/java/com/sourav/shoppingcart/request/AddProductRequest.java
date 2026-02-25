package com.sourav.shoppingcart.request;

import com.sourav.shoppingcart.model.Category;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory; // quantity
    private String description;
    private Category category;
}
