package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.domain.entity.Menu;
import com.felix.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:MenuController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/23 18:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/menu")
@Api(tags = "菜单接口")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    @ApiOperation(value = "查询菜单列表")
    public ResponseResult getMenuList(@Param("status") String status,@Param("menuName") String menuName){
        return menuService.getMenuList(status,menuName);
    }

    @PostMapping("")
    @ApiOperation(value = "新增菜单")
    public ResponseResult increaseMenu(@RequestBody Menu menu){
        return menuService.increaseMenu(menu);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询菜单")
    public ResponseResult getMenuById(@PathVariable("id") Long id){
        return menuService.getMenuById(id);
    }

    @PutMapping("")
    @ApiOperation(value = "修改菜单")
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{menuId}")
    @ApiOperation(value = "删除菜单")
    public ResponseResult deleteMenuByMenuId(@PathVariable Long menuId){
        return menuService.deleteMenuByMenuId(menuId);
    }

    @GetMapping("/treeselect")
    @ApiOperation(value = "获取菜单树")
    public ResponseResult treeselect(){
        return menuService.treeselect();
    }

    //写的不太好 要求不符
    @GetMapping("/roleMenuTreeselect/{id}")
    @ApiOperation(value = "加载对应角色菜单列表树接口")
    public ResponseResult roleMenuTreeselect(@PathVariable("id") Long id){
        return menuService.roleMenuTreeselect(id);
    }
}
