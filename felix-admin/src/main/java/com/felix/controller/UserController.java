package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddUserDto;
import com.felix.domain.dto.UserDto;
import com.felix.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:UserController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/25 22:11
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation(value = "用户分页列表接口")
    public ResponseResult getUserList(Integer pageNum, Integer pageSize, UserDto userDto){
        return userService.getUserList(pageNum,pageSize,userDto);
    }

    //TODO 都是一对多关系 要到xml文件中编写代码
    @PostMapping("")
    @ApiOperation(value = "新增用户")
    public ResponseResult addUser(@RequestBody AddUserDto addUserDto){
        return null;
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除用户")
    public ResponseResult deleteUserById(@PathVariable("id") Long id){
        return userService.deleteUserById(id);
    }

    //TODO 修改用户

}
