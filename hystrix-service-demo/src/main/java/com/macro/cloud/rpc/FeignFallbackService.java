package com.macro.cloud.rpc;

import com.macro.cloud.domain.CommonResult;
import com.macro.cloud.domain.User;
import org.springframework.stereotype.Component;

@Component
public class FeignFallbackService implements IFeignService{
    @Override
    public CommonResult<User> getUser(Long id) {
        User defaultUser = new User(-1L, "降级值", "123456");
        return new CommonResult<>(defaultUser);
    }
}
