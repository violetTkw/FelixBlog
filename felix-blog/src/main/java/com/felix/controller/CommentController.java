package com.felix.controller;

import com.felix.constants.SystemConstants;
import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddCommentDto;
import com.felix.domain.entity.Comment;
import com.felix.service.CommentService;
import com.felix.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:CommentController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/9 15:02
 * @Version 1.0
 */
@Api(tags = "评论模块")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "获取文章评论")
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    @ApiOperation(value = "增加评论")
    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto commentDto){
        Comment comment = BeanCopyUtils.copyBean(commentDto, Comment.class);
        return commentService.addComment(comment);
    }

    @ApiOperation(value = "获取友链评论")
    @GetMapping("/linkCommentList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "页号"),
            @ApiImplicitParam(name = "pageSize",value = "每页大小")
    })
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }
}
