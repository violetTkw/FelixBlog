package com.felix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felix.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-07 21:24:51
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}

