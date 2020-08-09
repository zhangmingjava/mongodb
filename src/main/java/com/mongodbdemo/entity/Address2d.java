package com.mongodbdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author: wr
 * @description:
 * @date: 2020-08-01 12:39
 * @since:
 */
@Data
public class Address2d {
    @Id
    private String id;
    private String name;
    private int age;
    // 2dsphere
    //必须强制命名为location,否则mongTemplate不识别,会报错,还有就是point的包别导错了.
    private double [] location;

    public Address2d(String id, String name, int age, double []location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public Address2d() {
    }
}
