package com.felix.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.felix.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:ArticleMapper
 * Package:com.felix.mapper
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/4 22:52
 * @Version 1.0
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
