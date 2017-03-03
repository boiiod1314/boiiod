package com.boiiod.service.impl;

import com.boiiod.mapper.CategoryMapper;
import com.boiiod.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    public CategoryMapper getCategoryMapper() {
        return this.categoryMapper;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}