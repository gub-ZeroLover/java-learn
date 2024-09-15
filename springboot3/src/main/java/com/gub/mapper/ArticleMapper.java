package com.gub.mapper;

import com.gub.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {


    @Select("insert into article (title, content, cover_img, state, category_id, create_time, update_time, create_user) " +
            "values (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, now(), now(),#{createUser} )")
    void add(Article article);


    @Select("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}," +
            " category_id = #{categoryId}, update_time = now() where id = #{id}")
    void update(Article article);

    @Select("select * from article where id = #{id}")
    Article findById(Integer id);

    @Select("delete from article where id = #{id}")
    void delete(Integer id);

    List<Article> list(Integer categoryId, String state, Integer userId);
}
