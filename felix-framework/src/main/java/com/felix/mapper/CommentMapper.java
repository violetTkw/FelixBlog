package com.felix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felix.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;



/**
 * 评论表(Comment)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-09 10:55:54
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}

