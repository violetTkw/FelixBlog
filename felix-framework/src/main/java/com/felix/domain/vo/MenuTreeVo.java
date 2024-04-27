package com.felix.domain.vo;

import com.felix.domain.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName:MenuTreeVo
 * Package:com.felix.domain.vo
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/27 9:30
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuTreeVo {
    private List<List<Menu>> menus;
    private List<Long> checkedKeys;
}
