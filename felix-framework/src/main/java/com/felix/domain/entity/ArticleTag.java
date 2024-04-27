package com.felix.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章标签关联表(SgArticleTag)表实体类
 *
 * @author makejava
 * @since 2024-04-23 09:54:33
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sg_article_tag")
public class ArticleTag extends Model<ArticleTag> {
    //文章id
    @TableId("article_id")
    private Long articleId;
    //标签id
    private Long tagId;

}

