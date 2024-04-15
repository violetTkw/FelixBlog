package com.felix.service;

import com.felix.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:UploadService
 * Package:com.felix.service
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/10 22:49
 * @Version 1.0
 */
public interface UploadService {
    ResponseResult uploadImg(MultipartFile img);
}
