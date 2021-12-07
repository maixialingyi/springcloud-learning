package com.macro.cloud.rpc;

import com.macro.cloud.domain.CommonResult;
import com.macro.cloud.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service",fallback = FeignFallbackService.class)
public interface IFeignService {

    @GetMapping("/user/{id}")
    CommonResult<User> getUser(@PathVariable Long id);
}
