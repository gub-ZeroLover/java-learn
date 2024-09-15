package com.gub.service.serviceImpl;

import com.gub.mapper.CategoryMapper;
import com.gub.pojo.Category;
import com.gub.service.CategoryService;
import com.gub.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {

        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        category.setCreateUser(userId);

        categoryMapper.add(category);

    }

    @Override
    public List<Category> list() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer)claims.get("id");
        return categoryMapper.list(userId);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public Category detail(Integer id) {

        return categoryMapper.detail(id);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);

    }
}
