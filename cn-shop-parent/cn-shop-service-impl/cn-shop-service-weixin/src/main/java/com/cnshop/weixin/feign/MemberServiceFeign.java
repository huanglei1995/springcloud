package com.cnshop.weixin.feign;

import com.cnshop.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * create by hl on 2019/8/18 22:58
 * @descript
 */
@FeignClient("app-cnshop-number")
public interface MemberServiceFeign extends MemberService {
}
