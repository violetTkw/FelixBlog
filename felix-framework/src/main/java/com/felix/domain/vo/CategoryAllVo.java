package com.felix.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:CategoryAllVo
 * Package:com.felix.domain.vo
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/22 14:53
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAllVo {
    private Long id;
    private String name;
    private String description;
}
