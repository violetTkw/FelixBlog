package com.felix.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddRoleDto;
import com.felix.domain.dto.RoleDto;
import com.felix.domain.dto.RoleStatusDto;
import com.felix.domain.dto.UpdateRoleDto;
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

    ResponseResult getRoleList(Integer pageNum, Integer pageSize, RoleDto roleDto);

    ResponseResult changeStatus(RoleStatusDto roleStatusDto);

    ResponseResult increaseRole(AddRoleDto addRoleDto);

    ResponseResult getRoleById(Long id);

    ResponseResult deleteRoleById(Long id);

    ResponseResult listAllRole();

    ResponseResult updateRole(UpdateRoleDto updateRoleDto);
}

