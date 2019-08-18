package com.cnshop.member.service;

import com.cnshop.core.base.BaseResponse;
import com.cnshop.entity.AppEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @auther: 黄磊
 * @date: 2019/8/12 20:22
 * @description: 会员服务接口
 */
@Api(tags = "会员服务接口")
public interface MemberService {

    /**
     * 会员调用微信
     * @return
     */
    @ApiOperation(value = "会员服务调用微信服务")
    @GetMapping("/memberToWeixin")
    public BaseResponse<AppEntity> memberToWeixin();
}
