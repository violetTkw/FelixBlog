package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddArticleDto;
import com.felix.domain.dto.ArticleListDto;
import com.felix.domain.dto.UpdateArticleDto;
import com.felix.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:ArticleController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/22 23:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/article")
@Api(tags = "博文接口")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    @PostMapping()
    @ApiOperation(value = "新增博文")
    public ResponseResult addArticle(@RequestBody AddArticleDto addArticleDto){
        return articleService.addArticle(addArticleDto);
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询文章")
    public ResponseResult getArticleList(Integer pageNum, Integer pageSize, ArticleListDto articleListDto){
        return articleService.getArticleList(pageNum,pageSize,articleListDto);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "获得文章详情")
    public ResponseResult getArticleDetailById(@PathVariable("id") Long id){
        return articleService.getArticleDetailById(id);
    }

    @ApiOperation(value = "更新文章接口")
    @PutMapping("")
    public ResponseResult updateArticle(@RequestBody UpdateArticleDto updateArticleDto){
        return articleService.updateArticle(updateArticleDto);
    }

    @ApiOperation(value = "删除文章接口")
    @DeleteMapping("/{id}")
    public ResponseResult deleteArticle(@PathVariable("id") Long id){
        return articleService.deleteArticle(id);
    }
}
