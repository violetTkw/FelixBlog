package com.felix.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felix.domain.entity.ArticleTag;

import com.felix.mapper.ArticleTagMapper;
import com.felix.service.ArticleTagService;

import org.springframework.stereotype.Service;

/**
 * 文章标签关联表(SgArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2024-04-23 09:54:37
 */
@Service("sgArticleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

}

