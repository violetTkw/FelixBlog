package com.felix.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.felix.domain.ResponseResult;
import com.felix.domain.dto.TagListDto;
import com.felix.domain.entity.Tag;
import com.felix.domain.vo.PageVo;
import com.felix.domain.vo.TagVo;

/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2024-04-13 10:24:12
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult increaseTag(Tag tag);

    ResponseResult deleteTagById(Long id);

    ResponseResult getTagInfo(Long id);

    ResponseResult updateTageInfo(TagVo tagVo);

    ResponseResult listAllTag();
}

