package com.felix.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felix.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;


/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-07 09:59:00
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

