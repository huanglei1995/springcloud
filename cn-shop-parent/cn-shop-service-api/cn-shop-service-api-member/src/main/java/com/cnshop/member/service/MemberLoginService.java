package com.cnshop.member.service;

import com.alibaba.fastjson.JSONObject;
import com.cnshop.base.BaseResponse;
import com.cnshop.member.input.dto.UserLoginInpDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * create by hl on 2019/8/19 23:15
 *
 * @descript
 */
@Api(tags = "用户登录服务接口")
public interface MemberLoginService {

    /**
     * 用户登陆接口
     * @param userLoginInpDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "会员用户登陆信息接口")
    BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);
}
