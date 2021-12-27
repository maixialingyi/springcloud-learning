package com.macro.cloud.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.macro.cloud.domain.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentinelResourceMoreB {

    @Autowired
    private SentinelResourceMoreC moreC;

    @SentinelResource(value = "moreB")
    public void moreB() {
        System.out.println("moreB");
        moreC.moreC();
    }

}
