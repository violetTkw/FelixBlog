package com.felix.service.impl;

import com.felix.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:PermissionService
 * Package:com.felix.service.impl
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/15 16:14
 * @Version 1.0
 */
@Service("ps")
public class PermissionService {
    /**
     * 判断当前用户是否具有permission
     * @param permission 要判断的权限
     * @return
     */
    public boolean hasPermission(String permission){
        //如果是超级管理员直接返回true
        if(SecurityUtils.isAdmin()){
            return true;
        }

        //否则 获取当前登录用户所具有的权限列表 如何判断是否存在permission
        List<String> permissions = SecurityUtils.getLoginUser().getPermission();
        return permissions.contains(permission);
    }
}
