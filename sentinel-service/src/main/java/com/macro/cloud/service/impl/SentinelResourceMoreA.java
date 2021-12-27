package com.macro.cloud.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentinelResourceMoreA {

    @Autowired
    private SentinelResourceMoreC moreC;

    @SentinelResource(value = "moreA")
    public void moreA() {
        System.out.println("moreA");
        moreC.moreC();
    }

}
