package com.felix.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.felix.constants.SystemConstants;
import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddCategoryDto;
import com.felix.domain.dto.UpdateCategoryDto;
import com.felix.domain.entity.Article;
import com.felix.domain.vo.CategoryAllVo;
import com.felix.domain.vo.CategoryListVo;
import com.felix.domain.vo.CategoryVo;
import com.felix.domain.vo.PageVo;
import com.felix.enums.AppHttpCodeEnum;
import com.felix.service.ArticleService;
import com.felix.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felix.domain.entity.Category;
import com.felix.mapper.CategoryMapper;
import com.felix.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2024-04-07 09:59:13
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private ArticleService articleService;

    @Autowired CategoryMapper categoryMapper;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表 状态为已发布的文章
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articles = articleService.list(articleLambdaQueryWrapper);
        //获取文章的分类id 并且去重
        Set<Long> categoryIds = articles.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());
        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream()
                .filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public ResponseResult listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> categories = list(wrapper);
        List<CategoryAllVo> categoryAllVos = BeanCopyUtils.copyBeanList(categories, CategoryAllVo.class);
        return ResponseResult.okResult(categoryAllVos);
    }

    @Override
    public ResponseResult getCategoryPageList(Integer pageNum, Integer pageSize, String name, String status) {
        //能根据分类名称进行模糊查询。
        //能根据状态进行查询。
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name),Category::getName,name);
        queryWrapper.eq(StringUtils.hasText(status),Category::getStatus,status);
        Page<Category> page = new Page<>();
        page(page,queryWrapper);
        List<Category> categoryList = page.getRecords();
        List<CategoryListVo> categoryListVos = BeanCopyUtils.copyBeanList(categoryList, CategoryListVo.class);
        PageVo pageVo= new PageVo(categoryListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addCategory(AddCategoryDto addCategoryDto) {
        if(!addCategoryDto.getStatus().equals(SystemConstants.STATUS_NORMAL)){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"状态不正常,无法新增用户");
        }
        Category category = BeanCopyUtils.copyBean(addCategoryDto, Category.class);
        save(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getCategoryById(Long id) {
        //状态必须是正常的
        Category category = getById(id);
        if(!category.getStatus().equals(SystemConstants.STATUS_NORMAL)){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"状态不正常，查询失败");
        }
        CategoryListVo categoryListVo = BeanCopyUtils.copyBean(category, CategoryListVo.class);
        return ResponseResult.okResult(categoryListVo);
    }

    @Override
    public ResponseResult updateCategory(UpdateCategoryDto updateCategoryDto) {
        Category byId = getById(updateCategoryDto.getId());
        if(byId==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"id不存在");
        }
        Category category = BeanCopyUtils.copyBean(updateCategoryDto, Category.class);
        updateById(category);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteCategoryById(Long id) {
        Category category = getById(id);
        if(category==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"id不存在");
        }
        categoryMapper.deleteById(id);
        return ResponseResult.okResult();
    }
}

