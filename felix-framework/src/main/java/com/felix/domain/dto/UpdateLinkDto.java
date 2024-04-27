package com.felix.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:UpdateLinkDto
 * Package:com.felix.domain.dto
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/26 17:32
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLinkDto {
    private Long id;

    private String name;

    private String logo;

    private String description;
    //网站地址
    private String address;
    //审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
    private String status;
}
