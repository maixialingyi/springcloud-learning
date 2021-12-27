package com.macro.cloud.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by macro on 2019/8/29.
 */
@RestController
@RequestMapping("/two")
public class TwoController {

    @GetMapping("/name")
    public String getName() {
        return "success";
    }

}
