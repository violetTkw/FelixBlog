package com.felix.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:RoleDto
 * Package:com.felix.domain.dto
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/24 9:55
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    //角色名称
    private String roleName;
    //角色状态（0正常 1停用）
    private String status;
}
