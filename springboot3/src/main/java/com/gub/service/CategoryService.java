package com.gub.service;

import com.gub.pojo.Category;

import java.util.List;

public interface CategoryService {

    //添加分类
    void add(Category category);

    List<Category> list();

    void update(Category category);

    Category detail(Integer id);

    void delete(Integer id);
}
