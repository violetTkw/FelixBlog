package com.felix.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName:AddUserDto
 * Package:com.felix.domain.dto
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/25 23:02
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {
    //用户名
    private String userName;
    //昵称
    private String nickName;
    //密码
    private String password;
    //账号状态（0正常 1停用）
    private String status;
    //邮箱
    private String email;
    //手机号
    private String phoneNumber;
    //用户性别（0男，1女，2未知）
    private String sex;

    //角色id
    List<Long> roleIds;
}
