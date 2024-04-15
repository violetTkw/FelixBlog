package com.felix.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.felix.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2024-04-14 09:05:20
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);
}

