package com.cnshop.member.feign;

import com.cnshop.member.service.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-cnshop-number")
public interface MemberRegisterServiceFeign extends MemberRegisterService {

}
