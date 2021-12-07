package com.macro.cloud.controller;

import com.macro.cloud.domain.CommonResult;
import com.macro.cloud.domain.User;
import com.macro.cloud.rpc.IFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feignHystrix")
public class FeignHystrixController {

    @Autowired
    private IFeignService iFeignService;

    @GetMapping("/getuser/{id}")
    public CommonResult<User> getuser(@PathVariable Long id){
        return iFeignService.getUser(id);
    }
}
