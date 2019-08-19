package com.cnshop.member.service.impl;

import com.cnshop.base.BaseApiService;
import com.cnshop.base.BaseResponse;
import com.cnshop.constant.Constants;
import com.cnshop.core.bean.CnShopBeanUtils;
import com.cnshop.member.mapper.UserMapper;
import com.cnshop.member.mapper.entity.UserDo;
import com.cnshop.member.output.dto.UserOutDto;
import com.cnshop.member.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: 黄磊
 * @date: 2019/8/12 20:30
 * @description: 会员实现类
 */
@RestController
public class MemberServiceImpl extends BaseApiService<UserOutDto> implements MemberService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<UserOutDto>  existMobile(String mobile) {
        // 1.验证参数
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        // 2.根据手机号码查询用户信息 单独定义code 表示是用户信息不存在把
        UserDo userEntity = userMapper.existMobile(mobile);
        if (userEntity == null) {
            return setResultError(Constants.HTTP_RES_CODE_EXISTMOBILE_203, "用户信息不存在!");
        }
        return setResultSuccess(CnShopBeanUtils.doToDto(userEntity, UserOutDto.class));
    }
}
