package com.felix.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:RoleStatusDto
 * Package:com.felix.domain.dto
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/24 10:59
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleStatusDto {
    //角色ID
    private Long id;
    //角色状态（0正常 1停用）
    private String status;
}
