package com.felix.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddCategoryDto;
import com.felix.domain.dto.UpdateCategoryDto;
import com.felix.domain.entity.Category;
import com.felix.domain.vo.ExcelCategoryVo;
import com.felix.enums.AppHttpCodeEnum;
import com.felix.service.CategoryService;
import com.felix.utils.BeanCopyUtils;
import com.felix.utils.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:CategoryController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/15 10:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/category")
@Api(tags = "分类功能")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    @ApiOperation(value = "导出分类")
    public void export(HttpServletResponse response){
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos =
                    BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(),
                            ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);
        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result =
                    ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }


    @GetMapping("/listAllCategory")
    @ApiOperation("查询所有分类")
    public ResponseResult listAllCategory() {
        return categoryService.listAllCategory();
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询分类列表")
    public ResponseResult getCategoryPageList(Integer pageNum,Integer pageSize,String name,String status){
        return categoryService.getCategoryPageList(pageNum,pageSize,name,status);
    }

    @PostMapping("")
    @ApiOperation(value = "新增分类")
    public ResponseResult addCategory(@RequestBody AddCategoryDto addCategoryDto){
        return categoryService.addCategory(addCategoryDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id进行查询")
    public ResponseResult getCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("")
    @ApiOperation(value = "更新分类")
    public ResponseResult updateCategory(@RequestBody UpdateCategoryDto updateCategoryDto){
        return categoryService.updateCategory(updateCategoryDto);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除某个分类")
    public ResponseResult deleteCategoryById(@PathVariable("id") Long id){
        return categoryService.deleteCategoryById(id);
    }
}
