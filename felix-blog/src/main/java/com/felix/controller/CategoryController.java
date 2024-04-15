package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:CategoryController
 * Package:com.felix.controller
 * Description:
 * 分类接口
 * @Author FelixT
 * @Create 2024/4/7 10:46
 * @Version 1.0
 */
@Api(tags = "分类模块")
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @ApiOperation(value = "获取分类信息")
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList(){
        return categoryService.getCategoryList();
    }
}
