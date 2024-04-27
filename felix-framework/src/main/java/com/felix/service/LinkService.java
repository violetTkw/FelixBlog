package com.felix.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddLinkDto;
import com.felix.domain.dto.UpdateLinkDto;
import com.felix.domain.entity.Link;

/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-04-07 21:25:10
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    ResponseResult getLinkList(Integer pageNum, Integer pageSize, String name, String status);

    ResponseResult addLink(AddLinkDto addLinkDto);

    ResponseResult getLinkById(Long id);

    ResponseResult updateLink(UpdateLinkDto updateLinkDto);

    ResponseResult deleteLinkById(Long id);
}

