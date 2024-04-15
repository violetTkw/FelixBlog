package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.domain.entity.User;
import com.felix.enums.AppHttpCodeEnum;
import com.felix.exception.SystemException;
import com.felix.service.BlogLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:BlogLoginController
 * Package:com.felix.controller
 * Description:
 *登录接口
 * @Author FelixT
 * @Create 2024/4/8 9:09
 * @Version 1.0
 */
@Api(tags = "前台登录模块")
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @ApiOperation(value = "登录功能")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
