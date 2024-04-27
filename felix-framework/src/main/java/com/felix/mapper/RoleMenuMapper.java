package com.felix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felix.domain.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和菜单关联表(RoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-24 18:02:47
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}

