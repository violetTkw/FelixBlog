package com.felix.service;

import com.felix.domain.ResponseResult;
import com.felix.domain.entity.User;

/**
 * ClassName:LoginService
 * Package:com.felix.service
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/13 17:00
 * @Version 1.0
 */
public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}
