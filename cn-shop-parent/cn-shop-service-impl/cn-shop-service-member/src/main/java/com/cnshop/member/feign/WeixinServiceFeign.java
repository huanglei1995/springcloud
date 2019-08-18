package com.cnshop.member.feign;

import com.cnshop.weixin.service.WeiXinService;
import org.springframework.cloud.openfeign.FeignClient;

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
