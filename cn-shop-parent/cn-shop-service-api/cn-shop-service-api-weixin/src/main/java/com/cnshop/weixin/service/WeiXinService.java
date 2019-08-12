package com.cnshop.weixin.service;

import com.cnshop.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther: 黄磊
 * @date: 2019/8/11 22:42
 * @description: 微信公共服务接口
 */
@Api(tags = "微信服务接口")
public interface WeiXinService {

    @GetMapping("/getApp")
    @ApiOperation(value = "微信应用服务接口")
    public AppEntity getApp();
}
