package com.mongodbdemo.spi;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

/**
 * @author: wr
 * @description:
 * @date: 2020-08-09 11:49
 * @since:
 */
public class SpringFactoriesTest {

    public static void main(String [] args){
        List<PropertySourceLoader> propertySourceLoaders = SpringFactoriesLoader.loadFactories(PropertySourceLoader.class,
                SpringFactoriesTest.class.getClassLoader());
        System.out.println(propertySourceLoaders);

    }
}
