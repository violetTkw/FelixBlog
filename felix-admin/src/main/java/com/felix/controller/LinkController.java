package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddLinkDto;
import com.felix.domain.dto.UpdateLinkDto;
import com.felix.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:LinkController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/26 16:33
 * @Version 1.0
 */
@RestController
@Api(tags = "友链管理")
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    @ApiOperation(value = "分页查询友链列表")
    public ResponseResult getLinkList(Integer pageNum,Integer pageSize,String name,String status){
        return linkService.getLinkList(pageNum,pageSize,name,status);
    }
    @PostMapping("")
    @ApiOperation(value = "新增友链")
    public ResponseResult addLink(@RequestBody AddLinkDto addLinkDto) {
        return linkService.addLink(addLinkDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询友链")
    public ResponseResult getLinkById(@PathVariable("id") Long id){
        return linkService.getLinkById(id);
    }

    @PutMapping("")
    @ApiOperation(value = "修改友链")
    public ResponseResult updateLink(@RequestBody UpdateLinkDto updateLinkDto){
        return linkService.updateLink(updateLinkDto);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除友链")
    public ResponseResult deleteLinkById(@PathVariable("id") Long id){
        return linkService.deleteLinkById(id);
    }
}
