package com.sourav.shoppingcart.service.category;

import com.sourav.shoppingcart.model.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryBYName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category , Long categoryId);
    void deleteCategory(Long id);

}
