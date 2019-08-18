package com.cnshop.member.service;

import com.alibaba.fastjson.JSONObject;
import com.cnshop.core.base.BaseResponse;
import com.cnshop.member.entity.UserEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * create by hl on 2019/8/18 21:16
 *
 * @descript
 */
@ApiOperation(value = "会员注册接口")
public interface MemberRegisterService {

    /**
     * 用户注册接口
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "会员用户注册信息接口")
    BaseResponse<JSONObject> register(@RequestBody UserEntity userEntity,
                                      @RequestParam("registCode") String registCode);
}
