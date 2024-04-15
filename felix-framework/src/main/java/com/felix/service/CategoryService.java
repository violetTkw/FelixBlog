package com.felix.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.felix.domain.ResponseResult;
import com.felix.domain.entity.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2024-04-07 09:59:13
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

