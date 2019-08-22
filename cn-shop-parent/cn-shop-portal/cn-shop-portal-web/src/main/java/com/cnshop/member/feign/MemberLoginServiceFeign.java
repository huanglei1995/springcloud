package com.cnshop.member.feign;

import com.cnshop.member.service.MemberLoginService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-cnshop-number")
public interface MemberLoginServiceFeign extends MemberLoginService {

}
