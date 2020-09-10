package com.cutety.service.impl;

import com.cutety.entity.Categories;
import com.cutety.mapper.CategoriesMapper;
import com.cutety.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoriesImpl implements CategoriesService {
    @Autowired
    private CategoriesMapper categoriesMapper;
    @Override
    public List<Categories> getCategories() {
        return categoriesMapper.getCategories();
    }
}
