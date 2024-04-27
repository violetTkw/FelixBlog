package com.felix.controller;

import com.felix.domain.ResponseResult;
import com.felix.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:UploadController
 * Package:com.felix.controller
 * Description:
 *
 * @Author FelixT
 * @Create 2024/4/22 15:50
 * @Version 1.0
 */
@RestController
@Api("/上传接口")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "上传图片到Oss功能")
    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img){
        return uploadService.uploadImg(img);
    }
}
