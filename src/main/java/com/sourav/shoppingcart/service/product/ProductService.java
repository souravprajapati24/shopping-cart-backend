package com.sourav.shoppingcart.service.product;
import com.sourav.shoppingcart.Exception.AlreadyExistsException;
import com.sourav.shoppingcart.Exception.ResourceNotFoundException;
import com.sourav.shoppingcart.dto.ImageDto;
import com.sourav.shoppingcart.dto.ProductDto;
import com.sourav.shoppingcart.model.Category;
import com.sourav.shoppingcart.model.Image;
import com.sourav.shoppingcart.model.Product;
import com.sourav.shoppingcart.repository.categoryRepository.CategoryRepository;
import com.sourav.shoppingcart.repository.imageRepository.ImageRepository;
import com.sourav.shoppingcart.repository.productRepository.ProductRepository;
import com.sourav.shoppingcart.request.AddProductRequest;
import com.sourav.shoppingcart.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor  // required for dependency injection through constructor
public class ProductService implements IProductService{

    private final ProductRepository productRepository; // injected via constructor
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product addProduct(AddProductRequest request) {
        // check if thr category is found in the Db
        // if yes then set it in the product
        // if not , then save it in db as a new category  then set it as product category

        if(productExist(request.getName(),request.getBrand())){
            throw new AlreadyExistsException(request.getBrand()+" "+request.getName()+" already exist , you may update product instead !");
        }
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });

        request.setCategory(category);
        return productRepository.save(createProduct(request,category));
    }

    private boolean productExist(String name ,String brand){
        return productRepository.existsByNameAndBrand(name,brand);
    }

    private Product createProduct(AddProductRequest request, Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request , Long productId){
        return productRepository.findById(productId)
                .map(existingProduct->updateExistingProject(existingProduct , request)) // map here is transforming the existing product into a updated product
                .map(productRepository :: save)
                .orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
    }

    private Product updateExistingProject(Product existingProduct , ProductUpdateRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public Product getProductById(Long id) {

        return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
    }

    @Override
    public void deleteProductById(Long id) {

        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete , ()-> {throw new ResourceNotFoundException("Product Not Found!");});
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }

    @Override
    public List<ProductDto> getConvertedToProducts(List<Product> products){
        return products.stream().map(this::convertToDto).toList();
    } // for converting the list of the products at once to dto


    @Override
    public ProductDto convertToDto(Product product){
        ProductDto productDto = modelMapper.map(product,ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> modelMapper.map(image,ImageDto.class))
                .toList();

        productDto.setImages(imageDtos);
        return productDto;  // to convert the one product to a productDto
    }




















}
