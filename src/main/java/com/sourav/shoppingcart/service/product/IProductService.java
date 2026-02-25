package com.sourav.shoppingcart.service.product;

import com.sourav.shoppingcart.dto.ProductDto;
import com.sourav.shoppingcart.model.Product;
import com.sourav.shoppingcart.request.AddProductRequest;
import com.sourav.shoppingcart.request.ProductUpdateRequest;

import java.util.List;


public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest request , Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductByBrand(String brand);
    List<Product> getProductByCategoryAndBrand(String category , String brand);
    List<Product> getProductByName(String name);
    List<Product> getByBrandAndName(String brand , String name);
    Long countProductByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedToProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
