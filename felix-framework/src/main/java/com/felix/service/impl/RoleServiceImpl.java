package com.felix.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felix.constants.SystemConstants;
import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddRoleDto;
import com.felix.domain.dto.RoleDto;
import com.felix.domain.dto.RoleStatusDto;
import com.felix.domain.dto.UpdateRoleDto;
import com.felix.domain.entity.RoleMenu;
import com.felix.domain.vo.PageVo;
import com.felix.domain.vo.RoleVo;
import com.felix.enums.AppHttpCodeEnum;
import com.felix.mapper.RoleMapper;
import com.felix.domain.entity.Role;
import com.felix.service.RoleMenuService;
import com.felix.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.felix.service.RoleService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2024-04-14 09:05:21
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id==1){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, RoleDto roleDto) {
        //需要有角色列表分页查询的功能。
        //要求能够针对角色名称进行模糊查询。
        //要求能够针对状态进行查询。
        //要求按照role_sort进行升序排列。
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(roleDto.getRoleName()),Role::getRoleName,roleDto.getRoleName());
        queryWrapper.eq(StringUtils.hasText(roleDto.getStatus()),Role::getStatus,roleDto.getStatus());
        queryWrapper.orderByAsc(Role::getRoleSort);
        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult changeStatus(RoleStatusDto roleStatusDto) {
        Role role = BeanCopyUtils.copyBean(roleStatusDto, Role.class);
        if (role==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"角色不存在,更新失败");
        }
        updateById(role);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult increaseRole(AddRoleDto addRoleDto) {
        //新增角色
        Role role = BeanCopyUtils.copyBean(addRoleDto, Role.class);
        save(role);
        //获取menuId以及RoleMenu的对象
        List<RoleMenu> roleMenuList = addRoleDto.getMenuIds().stream()
                .map(menuId -> new RoleMenu(role.getId(), menuId))
                .collect(Collectors.toList());
        //保存到sys_role_menu表中 有问题
        roleMenuService.saveBatch(roleMenuList);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getRoleById(Long id) {
        Role role = getById(id);
        RoleVo roleVo = BeanCopyUtils.copyBean(role, RoleVo.class);
        return ResponseResult.okResult(roleVo);
    }

    @Override
    public ResponseResult deleteRoleById(Long id) {
        boolean res = roleMapper.deleteById(id)>0;
        if(res){
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"删除失败");
    }

    @Override
    public ResponseResult listAllRole() {
        //查询的是所有状态正常的角色
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getStatus, SystemConstants.STATUS_NORMAL);
        List<Role> roleList = list(queryWrapper);
        return ResponseResult.okResult(roleList);
    }

    @Override
    public ResponseResult updateRole(UpdateRoleDto updateRoleDto) {
        //更新角色
//        Role role = BeanCopyUtils.copyBean(updateRoleDto, Role.class);
//        updateById(role);
        //获取menuId及对应的RoleMenu对象
        List<RoleMenu> roleMenuList = updateRoleDto.getMenuIds().stream()
                .map(menuId -> new RoleMenu(updateRoleDto.getId(), menuId))
                .collect(Collectors.toList());
        //更新sys_role_menu表  有问题要到xml文件中编写代码
        roleMenuService.saveOrUpdateBatch(roleMenuList);
//        roleMenuService.updateBatchById(roleMenuList);
        return ResponseResult.okResult();
    }
}

