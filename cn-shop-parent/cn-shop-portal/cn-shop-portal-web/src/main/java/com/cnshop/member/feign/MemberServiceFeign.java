package com.cnshop.member.feign;

import com.cnshop.member.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("app-cnshop-number")
public interface MemberServiceFeign extends MemberService {

}
