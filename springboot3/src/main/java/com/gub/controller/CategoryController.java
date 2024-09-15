package com.gub.controller;

import com.gub.pojo.Result;
import com.gub.service.CategoryService;
import com.gub.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.gub.pojo.Category;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //添加分类, 要求需要参数非空校验
    @PostMapping
    public Result add(@RequestBody @Validated Category category){

        categoryService.add(category);
        return Result.success();

    }

    //查询分类
    @GetMapping
    public Result<List<Category>> list(){

        List<Category> cs = categoryService.list();

        return Result.success(cs);
    }

    //更新文章分类
    @PutMapping
    public Result update(@RequestBody @Validated Category category){

        categoryService.update(category);
        return Result.success();
    }

    //获取文章详细信息
    @GetMapping("/{detail}")
    public Result<Category> detail(@RequestParam Integer id){

        //教程里，detail改成findById,, 方面其他地方使用
        Category category = categoryService.detail(id);
        return Result.success(category);
    }

    // 删除文章分类
    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        categoryService.delete(id);
        return Result.success();
    }
}
