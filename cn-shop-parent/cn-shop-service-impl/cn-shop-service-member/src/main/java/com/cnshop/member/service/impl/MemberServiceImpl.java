package com.cnshop.member.service.impl;

import com.cnshop.core.base.BaseResponse;
import com.cnshop.entity.AppEntity;
import com.cnshop.member.service.MemberService;
import com.cnshop.member.feign.WeixinServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: 黄磊
 * @date: 2019/8/12 20:30
 * @description: 会员实现类
 */
@RestController
public class MemberServiceImpl implements MemberService {

    @Autowired
    private WeixinServiceFeign weixinServiceFeign;
    @Override
    public BaseResponse<AppEntity> memberToWeixin() {
        return weixinServiceFeign.getApp();
    }
}
