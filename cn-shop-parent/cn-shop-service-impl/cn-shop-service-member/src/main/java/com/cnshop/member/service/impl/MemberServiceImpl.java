package com.cnshop.member.service.impl;

import com.cnshop.base.BaseApiService;
import com.cnshop.base.BaseResponse;
import com.cnshop.constant.Constants;
import com.cnshop.core.bean.CnShopBeanUtils;
import com.cnshop.core.token.GenerateToken;
import com.cnshop.core.type.TypeCastHelper;
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

    @Autowired
    private GenerateToken generateToken;

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

    @Override
    public BaseResponse<UserOutDto> getInfo(String token) {
        // 1.验证token参数
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        // 2.使用token查询redis 中的userId
        String redisUserId = generateToken.getToken(token);
        if (StringUtils.isEmpty(redisUserId)) {
            return setResultError("token已经失效或者token错误!");
        }
        // 3.使用userID查询 数据库用户信息
        Long userId = TypeCastHelper.toLong(redisUserId);
        UserDo userDo = userMapper.findByUserId(userId);
        if (userDo == null) {
            return setResultError("用户不存在!");
        }
        // 下节课将 转换代码放入在BaseApiService
        return setResultSuccess(CnShopBeanUtils.doToDto(userDo, UserOutDto.class));
    }
    // token存放在PC端 cookie token 存放在安卓 或者IOS端 存放在本地文件中
    // 当前存在那些问题？ 用户如果退出或者修改密码、忘记密码的情况 对token状态进行标识
    // token 如何防止伪造 真正其实很难防御伪造 尽量实现在安全体系 xss 只能在一些某些业务模块上加上必须验证本人操作
}
