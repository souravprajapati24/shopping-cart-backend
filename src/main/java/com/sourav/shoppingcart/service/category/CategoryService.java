package com.sourav.shoppingcart.service.category;

import com.sourav.shoppingcart.Exception.AlreadyExistsException;
import com.sourav.shoppingcart.Exception.ResourceNotFoundException;
import com.sourav.shoppingcart.model.Category;
import com.sourav.shoppingcart.repository.categoryRepository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("CategoryNotFound"));
    }

    @Override
    public Category getCategoryBYName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(c->!categoryRepository.existsByName(c.getName()))
                .map(categoryRepository :: save).orElseThrow(()->new AlreadyExistsException(category.getName() +" Category Already Exists !"));
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        return Optional.ofNullable(getCategoryById(categoryId)) // if the category od found optional become category and if not it become empty
                .map(oldCategory->{
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow(()->new ResourceNotFoundException("Category Not Found !"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,
                ()->{throw new ResourceNotFoundException("Category Not Found !");}
                );
    }
}
