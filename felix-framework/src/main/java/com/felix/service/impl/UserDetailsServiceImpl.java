package com.felix.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.felix.constants.SystemConstants;
import com.felix.domain.entity.LoginUser;
import com.felix.domain.entity.User;
import com.felix.mapper.MenuMapper;
import com.felix.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * ClassName:UserDetailsServiceImpl
 * Package:com.felix.service.impl
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/8 9:32
 * @Version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户 如果没查到抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        //返回用户信息
        //查询权限信息封装
        //如果是后台用户才需要查询权限封装
        if(user.getType().equals(SystemConstants.ADMIN)){
            List<String> perms = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user,perms);
        }
        return new LoginUser(user,null);
    }
}
