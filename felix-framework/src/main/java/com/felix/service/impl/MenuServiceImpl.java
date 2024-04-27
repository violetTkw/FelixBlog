package com.felix.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felix.constants.SystemConstants;
import com.felix.domain.ResponseResult;
import com.felix.domain.vo.MenuTreeVo;
import com.felix.enums.AppHttpCodeEnum;
import com.felix.mapper.MenuMapper;
import com.felix.domain.entity.Menu;
import com.felix.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.felix.service.MenuService;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2024-04-14 08:45:16
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员 返回所有的权限
        if(id.equals(1L)){
            LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(Menu::getMenuType, SystemConstants.MENU_TYPE_MENU,SystemConstants.MENU_TYPE_BUTTON);
            queryWrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            List<Menu> menuList = list(queryWrapper);
            List<String> permissions = menuList.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return permissions;
        }
        //否则返回所具有的权限

        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否是管理员 返回所有符合要求的menu
        if(userId.equals(1L)){
            menus = menuMapper.selectAllRouterMenu();
        }else {
        //否则 获取当前用户具有的menu
            menus=menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        //构建tree
        //先找出第一层的菜单 然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menus,SystemConstants.MENU_PARENT_ID);
        return menuTree;
    }

    @Override
    public ResponseResult getMenuList(String status, String menuName) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(status),Menu::getStatus,status)
                .or()
                .like(StringUtils.hasText(menuName),Menu::getMenuName,menuName);
        queryWrapper.orderByDesc(Menu::getParentId,Menu::getOrderNum);
        List<Menu> menuList = list(queryWrapper);
        return ResponseResult.okResult(menuList);
    }

    @Override
    public ResponseResult increaseMenu(Menu menu) {
        //判断是否是正常状态
        if(!menu.getStatus().equals(SystemConstants.STATUS_NORMAL)){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"增加失败");
        }
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenuById(Long id) {
        Menu menu = getById(id);
        return ResponseResult.okResult(menu);
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        //不能把父菜单设置为当前菜单
        if(menu.getParentId().equals(menu.getId())){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,
                    "修改菜单失败，上级菜单不能选择自己");
        }
        updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenuByMenuId(Long menuId) {
        //存在子菜单不允许删除
        Menu menu = getById(menuId);
        boolean isHasChildren = hasChildren(menu);
        if(isHasChildren){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"存在子菜单不允许删除");
        }
        //删除
        boolean res = menuMapper.deleteById(menuId) >0 ;
        if(res){
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"删除失败");
    }

    @Override
    public ResponseResult treeselect() {
        MenuMapper mapper = getBaseMapper();
        List<Menu> menus = mapper.selectLabel();
        //TODO menuName转化为label输出
        List<Menu> menuTree = builderMenuTree(menus,SystemConstants.MENU_PARENT_ID);
        return ResponseResult.okResult(menuTree);
    }

    @Override
    public ResponseResult roleMenuTreeselect(Long id) { //id 角色id
        //获取角色id对应的菜单id列表
        List<Long> menuIdList = menuMapper.selectMenuIdByRoleId(id);
        //获取菜单树
        //获取所有菜单
        List<Menu> menuList = list();
        //构建tree
        List<List<Menu>> menus = menuIdList.stream()
                .map(menuId -> builderMenuTree(menuList, menuId))
                .collect(Collectors.toList());
        MenuTreeVo menuTreeVo = new MenuTreeVo();
        menuTreeVo.setMenus(menus);
        menuTreeVo.setCheckedKeys(menuIdList);
        return ResponseResult.okResult(menuTreeVo);
    }

    /**
     * 判断是否有子菜单
     * @param menu
     * @return
     */
    private boolean hasChildren(Menu menu) {
        //查询出所有的menu
        List<Menu> menuList = list();
        //遍历
        List<Boolean> list = menuList.stream()
                .map(menu1 -> menu1.getParentId().equals(menu.getId()))
                .collect(Collectors.toList());
        //判断是否有true 有则代表有子菜单
        return list.contains(true);
    }


    //构建返回的tree
    private List<Menu> builderMenuTree(List<Menu> menus,Long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId)) //筛选出第一层菜单的id
                .map(menu -> menu.setChildren(getChildren(menu, menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取传入menu的子menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu,List<Menu> menus) {
        List<Menu> menuList = menus.stream()
                .filter(m -> m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        return menuList;
    }
}

