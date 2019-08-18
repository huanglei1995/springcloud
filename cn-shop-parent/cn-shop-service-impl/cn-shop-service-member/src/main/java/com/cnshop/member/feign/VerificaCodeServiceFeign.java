package com.cnshop.member.feign;

import com.cnshop.weixin.service.VerificaCodeService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * create by hl on 2019/8/18 21:28
 *
 * @descript
 */
@FeignClient(name = "app-cnshop-weixin")
public interface VerificaCodeServiceFeign extends VerificaCodeService {
}
