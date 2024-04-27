package com.felix.domain.vo;

import com.felix.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName:MenuVo
 * Package:com.felix.domain.vo
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/24 11:39
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo {
    private List<MenuVo> children;
    private Long id;
    //父菜单ID
    private Long parentId;
    private String label;
}
