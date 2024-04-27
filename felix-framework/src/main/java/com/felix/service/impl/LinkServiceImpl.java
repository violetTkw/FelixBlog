package com.felix.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.felix.constants.SystemConstants;
import com.felix.domain.ResponseResult;
import com.felix.domain.dto.AddLinkDto;
import com.felix.domain.dto.UpdateLinkDto;
import com.felix.domain.vo.LinkVo;
import com.felix.domain.vo.PageVo;
import com.felix.enums.AppHttpCodeEnum;
import com.felix.mapper.LinkMapper;
import com.felix.domain.entity.Link;
import com.felix.service.LinkService;
import com.felix.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2024-04-07 21:25:10
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Autowired
    private LinkMapper linkMapper;

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> linkList = list(queryWrapper);
        //转换为vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }

    @Override
    public ResponseResult getLinkList(Integer pageNum, Integer pageSize, String name, String status) {
        //需要分页查询友链列表。
        //能根据友链名称进行模糊查询。
        //能根据状态进行查询
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(name),Link::getName,name);
        queryWrapper.eq(StringUtils.hasText(status),Link::getStatus,status);
        Page<Link> page = new Page<>();
        page(page,queryWrapper);
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addLink(AddLinkDto addLinkDto) {
        Link link = BeanCopyUtils.copyBean(addLinkDto, Link.class);
        save(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getLinkById(Long id) {
        Link link = getById(id);
        if(link==null||!link.getStatus().equals(SystemConstants.LINK_STATUS_NORMAL)){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"查询失败");
        }
        LinkVo linkVo = BeanCopyUtils.copyBean(link, LinkVo.class);
        return ResponseResult.okResult(linkVo);
    }

    @Override
    public ResponseResult updateLink(UpdateLinkDto updateLinkDto) {
        Link linkId = getById(updateLinkDto.getId());
        if(linkId==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"更新失败,友链不存在");
        }
        Link link = BeanCopyUtils.copyBean(updateLinkDto, Link.class);
        updateById(link);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteLinkById(Long id) {
        Link linkId = getById(id);
        if(linkId==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"删除失败,友链不存在");
        }
        linkMapper.deleteById(id);
        return ResponseResult.okResult();
    }
}

