package com.felix.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.felix.domain.ResponseResult;
import com.felix.domain.entity.User;
import org.springframework.stereotype.Service;

/**
 * ClassName:BlogLoginService
 * Package:com.felix.service
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/8 9:21
 * @Version 1.0
 */

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
