package com.felix.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName:AddCommentDto
 * Package:com.felix.domain.dto
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/13 8:57
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "添加评论dto")
public class AddCommentDto {
    private Long id;
    //评论类型（0代表文章评论，1代表友链评论）
    @ApiModelProperty("评论类型（0代表文章评论，1代表友链评论）")
    private String type;
    //文章id
    @ApiModelProperty("文章id")
    private Long articleId;
    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    //回复目标评论id
    private Long toCommentId;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
}
