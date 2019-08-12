package com.cnshop.weixin.service.impl;

import com.cnshop.entity.AppEntity;
import com.cnshop.weixin.service.WeiXinService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: 黄磊
 * @date: 2019/8/12 20:02
 * @description: 微信接口的实现类
 */
@RestController
public class WeixinServiceImpl implements WeiXinService {

    @Override
    public AppEntity getApp() {
        return new AppEntity("黄磊", "45231354");
    }
}
