package com.macro.cloud.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class SentinelResourceMoreC {

    @SentinelResource(value = "moreC")
    public void moreC() {
        System.out.println("moreC");

    }

}
