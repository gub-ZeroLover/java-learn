package com.gub.controller;


import com.gub.pojo.Article;
import com.gub.pojo.Category;
import com.gub.pojo.PageBean;
import com.gub.pojo.Result;
import com.gub.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/list")
    public Result<String> list(){
        return Result.success("文章列表");
    }


    //新增文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article){

        articleService.add(article);

        return Result.success();
    }

    //更新文章
    @PutMapping
    public Result update(@RequestBody @Validated Article article){

        articleService.update(article);

        return Result.success();
    }

    //查询文章详细信息
    @GetMapping("/{detail}")
    public Result<Article> detail(@RequestParam Integer id){
        Article article = articleService.findById(id);
        return Result.success(article);
    }

    // 删除文章
    @DeleteMapping
    public Result delete(@RequestParam Integer id){

        articleService.delete(id);
        return Result.success();
    }

    //动态条件查询、 分页查询
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize, @RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String state){

        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);

        return Result.success(pb);
    }
}
