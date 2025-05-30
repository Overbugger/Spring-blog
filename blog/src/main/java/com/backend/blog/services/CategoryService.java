package com.backend.blog.services;

import com.backend.blog.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category newCategory);
    void deleteCategory(Integer id);
}