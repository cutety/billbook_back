package com.cutety.mapper;

import com.cutety.entity.Categories;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CategoriesMapper {
    List<Categories> getCategories();
}
