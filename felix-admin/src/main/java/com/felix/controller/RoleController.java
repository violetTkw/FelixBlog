package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddRoleDto;
import com.felix.domain.dto.RoleDto;
import com.felix.domain.dto.RoleStatusDto;
import com.felix.domain.dto.UpdateRoleDto;
import com.felix.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:RoleController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/24 9:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/role")
@Api(tags = "角色接口")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    @ApiOperation(value = "分页查询角色列表")
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, RoleDto roleDto){
        return roleService.getRoleList(pageNum,pageSize,roleDto);
    }

    @PutMapping("/changeStatus")
    @ApiOperation(value = "修改角色的停启用状态")
    public ResponseResult changeStatus(@RequestBody RoleStatusDto roleStatusDto){
        return roleService.changeStatus(roleStatusDto);
    }

    @PostMapping("")
    @ApiOperation(value = "新增角色接口")
    public ResponseResult increaseRole(@RequestBody AddRoleDto addRoleDto){
        return roleService.increaseRole(addRoleDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "角色信息回显接口")
    public ResponseResult getRoleById(@PathVariable("id") Long id){
        return roleService.getRoleById(id);
    }


    @PutMapping("")
    @ApiOperation(value = "更新角色信息接口")
    public ResponseResult updateRole(@RequestBody UpdateRoleDto updateRoleDto){
        return roleService.updateRole(updateRoleDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    public ResponseResult deleteRoleById(@PathVariable("id") Long id){
        return roleService.deleteRoleById(id);
    }

    @GetMapping("/listAllRole")
    @ApiOperation(value = "查询角色列表接口")
    public ResponseResult listAllRole(){
        return roleService.listAllRole();
    }
}
