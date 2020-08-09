package com.mongodbdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wr
 * @description:
 * @date: 2020-08-09 13:49
 * @since:
 */
@RestController
public class EnvironmentController {

    @Autowired
    private Environment environment;

    @GetMapping("/env")
    public String get(){
        return environment.getProperty("bootstrapTest");
    }
}
