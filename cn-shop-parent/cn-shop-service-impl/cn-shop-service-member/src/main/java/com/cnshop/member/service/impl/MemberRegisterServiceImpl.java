package com.cnshop.member.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.cnshop.base.BaseApiService;
import com.cnshop.base.BaseResponse;
import com.cnshop.constant.Constants;
import com.cnshop.core.utils.MD5Util;
import com.cnshop.member.input.dto.UserInpDto;
import com.cnshop.member.mapper.UserMapper;
import com.cnshop.member.mapper.entity.UserDo;
import com.cnshop.member.service.MemberRegisterService;
import com.cnshop.member.feign.VerificaCodeServiceFeign;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by hl on 2019/8/18 21:19
 *
 * @descript
 */
@RestController
public class MemberRegisterServiceImpl extends BaseApiService<JSONObject> implements MemberRegisterService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerificaCodeServiceFeign verificaCodeServiceFeign;

    @Override
    @Transactional
    public BaseResponse<JSONObject> register(@RequestBody UserInpDto userInpDto, String registCode) {
        // 1.参数验证
        String userName = userInpDto.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return setResultError("用户名称不能为空!");
        }
        String mobile = userInpDto.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = userInpDto.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        // 2.验证码注册码是否正确 暂时省略 会员调用微信接口实现注册码验证
        BaseResponse<JSONObject> verificaWeixinCode = verificaCodeServiceFeign.verificaWeixinCode(mobile, registCode);
        if (!verificaWeixinCode.getCode().equals(Constants.HTTP_RES_CODE_200)) {
            return setResultError(verificaWeixinCode.getMsg());
        }
        // 3.对用户的密码进行加密 // MD5 可以解密 暴力破解
        String newPassword = MD5Util.MD5(password);
        userInpDto.setPassword(newPassword);

        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userInpDto, userDo);

        // 4.调用数据库插入数据
        return userMapper.register(userDo) > 0 ? setResultSuccess("注册成功") : setResultError("注册失败!");
    }
}
