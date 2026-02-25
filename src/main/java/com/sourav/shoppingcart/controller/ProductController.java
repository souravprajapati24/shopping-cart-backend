package com.sourav.shoppingcart.controller;
import com.sourav.shoppingcart.Exception.ResourceNotFoundException;
import com.sourav.shoppingcart.dto.ProductDto;
import com.sourav.shoppingcart.model.Product;
import com.sourav.shoppingcart.request.AddProductRequest;
import com.sourav.shoppingcart.request.ProductUpdateRequest;
import com.sourav.shoppingcart.response.ApiResponse;
import com.sourav.shoppingcart.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedToProducts(products);
        return ResponseEntity.ok(new ApiResponse("Success" , convertedProducts));
    }

    @GetMapping("/product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            ProductDto convertedProduct = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Found !" , convertedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request){
        try {
            Product product = productService.addProduct(request);
            ProductDto convertedProduct = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Add Product Success !",convertedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long productId){
        try {
            Product product = productService.updateProduct(request,productId);
            ProductDto convertedProduct = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Updated !",convertedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long productId){
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Deleted !",null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brand, @RequestParam String name){
        try {
            List<Product> products = productService.getByBrandAndName(brand,name);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found !",null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedToProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndName(@RequestParam String category, @RequestParam String brand){
        try {
            List<Product> products = productService.getProductByCategoryAndBrand(category,brand);
            List<ProductDto> convertedProducts = productService.getConvertedToProducts(products);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found !",null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name){
        try {
            List<Product> products = productService.getProductByName(name);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found !",null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedToProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/products/by-brand/")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand){
        try {
            List<Product> products = productService.getProductByBrand(brand);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found !",null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedToProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/products/{category}/all/products")
    public ResponseEntity<ApiResponse> getProductByCategory(@PathVariable String category){
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found !",null));
            }
            List<ProductDto> convertedProducts = productService.getConvertedToProducts(products);
            return ResponseEntity.ok(new ApiResponse("Success", convertedProducts));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/products/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductBYBrandAndName(@RequestParam String brand, @RequestParam String name){
        try {
            var productCount = productService.countProductByBrandAndName(brand,name);
            return ResponseEntity.ok(new ApiResponse("Product Count !", productCount));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }






}
