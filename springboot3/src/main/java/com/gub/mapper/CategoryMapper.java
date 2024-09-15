package com.gub.mapper;

import com.gub.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("insert into category (category_name, category_alias, create_user, create_time, update_time) " +
            "values(#{categoryName}, #{categoryAlias}, #{createUser}, now(), now())")
    void add(Category category);

    @Select("select * from category where create_user = #{userId}")
    List<Category> list(Integer userId);

    @Select("update category set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = now() where id = #{id}")
    void update(Category category);

    @Select("select * from category where id = #{id}")
    Category detail(Integer id);

    @Select("delete from category where id  = #{id}")
    void delete(Integer id);
}
