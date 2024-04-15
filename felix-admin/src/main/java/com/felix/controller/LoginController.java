package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.domain.entity.LoginUser;
import com.felix.domain.entity.Menu;
import com.felix.domain.entity.User;
import com.felix.domain.vo.AdminUserInfoVo;
import com.felix.domain.vo.RoutersVo;
import com.felix.domain.vo.UserInfoVo;
import com.felix.enums.AppHttpCodeEnum;
import com.felix.exception.SystemException;
import com.felix.service.LoginService;
import com.felix.service.MenuService;
import com.felix.service.RoleService;
import com.felix.utils.BeanCopyUtils;
import com.felix.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName:LoginController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/13 16:53
 * @Version 1.0
 */
@Api(tags = "后台登录模块")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "后台登录功能")
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user) {
        if (!StringUtils.hasText(user.getUserName())) {
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @ApiOperation(value = "返回用户信息")
    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        //获取当前登陆的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回

        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @ApiOperation("查看菜单路由")
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menu =menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menu));
    }
    @ApiOperation("退出登录功能")
    @PostMapping("/user/logout")
    public ResponseResult logout(){
       return loginService.logout();
    }
}
