package com.macro.cloud.controller;

import com.macro.cloud.service.TwoServiceFeign;
import com.macro.cloud.service.UserService;
import com.macro.cloud.service.impl.SentinelResourceMoreA;
import com.macro.cloud.service.impl.SentinelResourceMoreB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by macro on 2019/8/29.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TwoServiceFeign twoServiceFeign;

    @Autowired
    private SentinelResourceMoreB moreB;

    @Autowired
    private SentinelResourceMoreA moreA;

    @GetMapping("/two1")
    public String getUser() {
        return twoServiceFeign.getName();
    }

    @GetMapping("/more1")
    public String getMore() {
        System.out.println("more 请求");
        moreA.moreA();
        moreB.moreB();
        return "success";
    }

}
