package com.mongodbdemo;

import cn.hutool.core.lang.Snowflake;
import com.mongodbdemo.dao.AddressRepository;
import com.mongodbdemo.entity.Address;
import com.mongodbdemo.utils.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class MongodbDemoApplicationTests {

    private static final boolean TRUE = true;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private Snowflake snowflake;
    ;

    @Test
    void contextLoads() {
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Address> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            int finalI = i;
            executorService.submit(() -> {
                Point point = RandomUtils.randomLonLat(75.6666666, 76.6666666, 75.6666666, 76.6666666);
                snowflake.nextIdStr();
                Address address = new Address(snowflake.nextIdStr(), String.valueOf(finalI), finalI, point);
                addressRepository.save(address);
            });
        }
        while (TRUE) {

        }

    }


    @Test
    void findById() {

        Optional<Address> optionalAddress = addressRepository.findById("1000");
        if (optionalAddress.isPresent()) {
            System.out.println(optionalAddress.get());
        } else {
            System.out.println("不存在");
        }
    }


    @Test
    void geoFind() {
        //设置距离,查找3公里以内的所有商铺
        Point point = new Point(76.65, 76.56);
        Distance distance = new Distance(1, Metrics.KILOMETERS);
        GeoResults<Address> personList = addressRepository.findByLocationNear(point, distance);
        System.out.println(">>>" + personList);

        for (GeoResult<Address> geoResult : personList) {
            System.out.println(geoResult.getDistance().getUnit() + "---" + geoResult.getDistance().getValue());
        }


    }
}
