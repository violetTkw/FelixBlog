package com.felix.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName:TagVo
 * Package:com.felix.domain.vo
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/19 21:13
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagVo {

    private Long id;
    //标签名
    private String name;
    //备注
    private String remark;
}
