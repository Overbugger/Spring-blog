package com.backend.blog.services.impl;

import com.backend.blog.models.Category;
import com.backend.blog.repo.CategoryRepo;
import com.backend.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    public List<Category> listCategories() {
        return categoryRepo.findAllWithPostsCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category newCategory) {
        String categoryName = newCategory.getName();
        boolean alreadyExist = categoryRepo.existsByNameIgnoreCase(categoryName);
        if(alreadyExist) {
            throw new IllegalArgumentException("Category already exists with name: " + categoryName);
        }
        return categoryRepo.save(newCategory);
    }

    @Override
    public void deleteCategory(Integer id) {
        Optional<Category> category = categoryRepo.findById(id);
        if(category.isPresent()) {
            if(!category.get().getPosts().isEmpty()) {
                throw new IllegalArgumentException("Category has posts associated with it");
            }
        }
        categoryRepo.deleteById(id);
    }


}
