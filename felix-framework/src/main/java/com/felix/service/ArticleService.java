package com.felix.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.felix.domain.ResponseResult;
import com.felix.domain.entity.Article;

/**
 * ClassName:ArticleService
 * Package:com.felix.service
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/6 9:21
 * @Version 1.0
 */
public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);
}

