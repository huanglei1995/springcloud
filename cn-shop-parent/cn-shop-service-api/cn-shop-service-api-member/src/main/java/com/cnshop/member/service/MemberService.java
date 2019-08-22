package com.cnshop.member.service;

import com.cnshop.base.BaseResponse;
import com.cnshop.member.output.dto.UserOutDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther: 黄磊
 * @date: 2019/8/12 20:22
 * @description: 会员服务接口
 */
@Api(tags = "会员服务接口")
public interface MemberService {
    /**
     * 根据手机号码查询是否已经存在,如果存在返回当前用户信息
     * @param mobile
     * @return
     */
    @ApiOperation(value = "根据手机号码查询是否已经存在")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", name = "mobile", dataType = "String", required = true, value = "用户手机号码"), })
    @PostMapping("/existMobile")
    BaseResponse<UserOutDto> existMobile(@RequestParam("mobile") String mobile);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "/getUserInfo")
    BaseResponse<UserOutDto> getInfo(@RequestParam("token") String token);

}
