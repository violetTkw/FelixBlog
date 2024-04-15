package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:LinkController
 * Package:com.felix.controller
 * Description:
 * 友链接口
 * @Author FelixT
 * @Create 2024/4/7 20:53
 * @Version 1.0
 */
@Api(tags = "友链模块")
@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @ApiOperation(value = "获取所有友链")
    @GetMapping("getAllLink")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }
}
