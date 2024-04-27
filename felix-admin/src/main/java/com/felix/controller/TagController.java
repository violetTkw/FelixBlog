package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.domain.dto.TagListDto;
import com.felix.domain.entity.Tag;
import com.felix.domain.vo.PageVo;
import com.felix.domain.vo.TagVo;
import com.felix.service.TagService;
import com.felix.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:TagController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/13 10:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/content/tag")
@Api(tags = "标签功能")
public class TagController {
    @Autowired
    private TagService tagService;

    @ApiOperation(value = "分页查询标签")
    @GetMapping("list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @ApiOperation(value = "新增标签")
    @PostMapping("")
    public ResponseResult increaseTag(@RequestBody TagListDto tagListDto){
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        return tagService.increaseTag(tag);
    }

    @ApiOperation(value = "删除标签")
    @DeleteMapping("/{id}")
    public ResponseResult deleteTagById(@PathVariable("id") Long id){
        return tagService.deleteTagById(id);
    }

    @ApiOperation(value = "获取标签信息")
    @GetMapping("/{id}")
    public ResponseResult getTagInfo(@PathVariable("id") Long id){
        return tagService.getTagInfo(id);
    }

    @ApiOperation(value = "更新标签信息")
    @PutMapping()
    public ResponseResult updateTagInfo(@RequestBody TagVo tagVo){
        return tagService.updateTageInfo(tagVo);
    }
    @ApiOperation(value = "获取全部标签")
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        return tagService.listAllTag();
    }
}
