package com.felix.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:CategoryController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/15 10:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/category")
@Api(tags = "分类功能")
public class CategoryController {

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    @ApiOperation(value = "导出分类")
    public void export(){
        //设置下载文件的请求头

        //获取需要导出的数据

        //将数据写入excel中

        //如果出现异常也要响应json
    }
}
