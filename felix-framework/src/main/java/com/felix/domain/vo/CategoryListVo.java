package com.felix.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:CategoryListVo
 * Package:com.felix.domain.vo
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/26 10:43
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListVo {
    private Long id;
    //分类名
    private String name;
    //描述
    private String description;
    //状态0:正常,1禁用
    private String status;
}
