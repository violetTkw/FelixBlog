package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:ArticleController
 * Package:com.felix.controller
 * Description:
 * 文章接口
 * @Author FelixT
 * @Create 2024/4/6 9:25
 * @Version 1.0
 */
@Api(tags = "文章模块")
@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public String test(){
//       return articleService.list().toString();
//    }

    @ApiOperation(value = "获取热门文章")
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        //查询热门文章 封装成ResponseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    @ApiOperation(value = "获取文章列表")
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @ApiOperation(value = "获取文章详情")
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

    @ApiOperation(value = "更新浏览量")
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable Long id){
        return articleService.updateViewCount(id);
    }
}
