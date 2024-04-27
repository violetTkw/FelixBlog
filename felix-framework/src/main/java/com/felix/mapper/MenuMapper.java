package com.felix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felix.domain.entity.Menu;

import java.util.List;


/**
 * 菜单权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-14 08:45:14
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Menu> selectLabel();

    List<Long> selectMenuIdByRoleId(Long id);
}

