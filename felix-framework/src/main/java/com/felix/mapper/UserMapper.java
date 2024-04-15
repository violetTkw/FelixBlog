package com.felix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felix.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2024-04-07 21:56:16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

