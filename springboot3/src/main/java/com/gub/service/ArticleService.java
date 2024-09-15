package com.gub.service;

import com.gub.pojo.Article;
import com.gub.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    void update(Article article);

    Article findById(Integer id);

    void delete(Integer id);

    // 动态条件查询、 分页查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
