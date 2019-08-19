package com.cnshop.weixin.mp.handler;

import com.cnshop.base.BaseResponse;
import com.cnshop.constant.Constants;
import com.cnshop.core.utils.RedisUtils;
import com.cnshop.core.utils.RegexUtils;
import com.cnshop.member.output.dto.UserOutDto;
import com.cnshop.weixin.feign.MemberServiceFeign;
import com.cnshop.weixin.mp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Value("${cnshop.weixin.registration.code.message}")
    private String registrationCodeMessage;

    @Value("${cnshop.weixin.default.registration.code.message}")
    private String defaultRegistrationCodeMessage;

    @Autowired
    private MemberServiceFeign memberServiceFeign;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList()
                .getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser())
                    .toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        // 1. 获取微信客户端发送的消息
        String fromContent = wxMessage.getContent();
        // 2. 使用正则表达式验证消息是否为手机号
        if (RegexUtils.checkPhone(fromContent)) {
            // 1.根据手机号码调用会员服务接口查询用户信息是否存在
            BaseResponse<UserOutDto> reusltUserInfo = memberServiceFeign.existMobile(fromContent);
            if (reusltUserInfo.getCode().equals(Constants.HTTP_RES_CODE_200)) {
                return new TextBuilder().build("该手机号码" + fromContent + "已经存在!", wxMessage, weixinService);
            }
            if (!reusltUserInfo.getCode().equals(Constants.HTTP_RES_CODE_EXISTMOBILE_203)) {
                return new TextBuilder().build(reusltUserInfo.getMsg(), wxMessage, weixinService);
            }
            // 3. 如果是手机号，随机生成四位注册码
            int code = registerCode();
            String content = String.format(registrationCodeMessage, code);
            // 将验证码填写到redis中
            redisUtils.setString(Constants.WEIXINCODE_KEY + fromContent, String.valueOf(code), Constants.WEIXINCODE_TIMEOUT);
            return new TextBuilder().build(content, wxMessage, weixinService);
        }

        // 4. 默认回复
        return new TextBuilder().build(defaultRegistrationCodeMessage, wxMessage, weixinService);

    }

    // 获取注册验证码
    private int registerCode () {
        int registCode = (int)(Math.random() * 9000 + 1000);
        return registCode;

    }
}
