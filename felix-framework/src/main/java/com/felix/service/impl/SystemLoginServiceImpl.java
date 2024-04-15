package com.felix.service.impl;

import com.felix.domain.ResponseResult;
import com.felix.domain.entity.LoginUser;
import com.felix.domain.entity.User;
import com.felix.domain.vo.BlogUserLoginVo;
import com.felix.domain.vo.UserInfoVo;
import com.felix.service.LoginService;
import com.felix.utils.BeanCopyUtils;
import com.felix.utils.JwtUtil;
import com.felix.utils.RedisCache;
import com.felix.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ClassName:LoginServiceImpl
 * Package:com.felix.service.impl
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/13 17:01
 * @Version 1.0
 */
@Service
public class SystemLoginServiceImpl implements LoginService {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if(Objects.isNull(authentication)){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token返回
        Map<String,String> map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        //获取当前用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //获得用户id
        Long userId = loginUser.getUser().getId();
        //删除redis中用户信息
        redisCache.deleteObject("login:"+userId);
        return ResponseResult.okResult();
    }
}
