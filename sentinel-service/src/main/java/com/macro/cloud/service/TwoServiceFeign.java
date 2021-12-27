package com.macro.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by macro on 2019/9/5.
 */
@FeignClient(value = "sentinel-service-two")
public interface TwoServiceFeign {

    @GetMapping("/two/name")
    String getName();

}
