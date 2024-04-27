package com.felix.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felix.domain.entity.RoleMenu;
import com.felix.mapper.RoleMenuMapper;
import com.felix.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2024-04-24 18:02:51
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}

