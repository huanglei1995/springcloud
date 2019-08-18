package com.cnshop.weixin.service.impl;

import com.cnshop.core.base.BaseApiService;
import com.cnshop.core.base.BaseResponse;
import com.cnshop.entity.AppEntity;
import com.cnshop.weixin.service.WeiXinService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: 黄磊
 * @date: 2019/8/12 20:02
 * @description: 微信接口的实现类
 */
@RestController
public class WeixinServiceImpl extends BaseApiService<AppEntity> implements WeiXinService {

    @Override
    public BaseResponse<AppEntity> getApp() {
        return setResultSuccess(new AppEntity("黄磊", "45231354"));
    }
}
