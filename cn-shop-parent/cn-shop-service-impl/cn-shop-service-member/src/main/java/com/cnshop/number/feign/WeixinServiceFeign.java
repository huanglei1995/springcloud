package com.cnshop.number.feign;

import com.cnshop.entity.AppEntity;
import com.cnshop.weixin.service.WeiXinService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther: 黄磊
 * @date: 2019/8/12 20:35
 * @description:
 */
@FeignClient("app-cnshop-weixin")
public interface WeixinServiceFeign extends WeiXinService {

//    @GetMapping("/getApp")
//    public AppEntity getApp();
}
