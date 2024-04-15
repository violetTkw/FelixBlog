package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.domain.dto.TagListDto;
import com.felix.domain.vo.PageVo;
import com.felix.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
