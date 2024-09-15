package com.gub.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gub.mapper.ArticleMapper;
import com.gub.pojo.Article;
import com.gub.pojo.PageBean;
import com.gub.service.ArticleService;
import com.gub.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {

        //从threadlocal中获取用户信息
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer)claims.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);


    }

    @Override
    public void update(Article article) {

        articleMapper.update(article);

    }

    @Override

    public Article findById(Integer id) {


        return articleMapper.findById(id);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);

    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {

        //分页查询
        PageBean<Article> pageBean = new PageBean<>();

        PageHelper.startPage(pageNum, pageSize);

        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer)claims.get("id");

        List<Article> articles = articleMapper.list(categoryId, state, userId);
        //強轉 使用 getTotal(), getResult() 方法
        Page<Article> p = (Page<Article>) articles;
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());

        return pageBean;
    }
}
