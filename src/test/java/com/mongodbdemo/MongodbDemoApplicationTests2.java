package com.mongodbdemo;

import com.mongodbdemo.dao.Address2dRepository;
import com.mongodbdemo.entity.Address2d;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
class MongodbDemoApplicationTests2 {

    @Autowired
    private Address2dRepository addressRepository;

    @Test
    void contextLoads() {
        Random random=new Random();
        List<Address2d>list=new ArrayList<>();
        for(int i=0;i<100;i++){
            double [] point=new double[]{random.nextInt(180), random.nextInt(90)};
            Address2d address=new Address2d(String.valueOf(i),String.valueOf(i),i,point);
            list.add(address);
        }
        addressRepository.saveAll(list);
    }


    @Test
    void findById() {

        Optional<Address2d> optionalAddress = addressRepository.findById("1000");
        if (optionalAddress.isPresent()){
            System.out.println(optionalAddress.get());
        }else{
            System.out.println("不存在");
        }
    }




    @Test
    void geoFind() {
        //设置距离,查找3公里以内的所有商铺

        Point point = new Point(180,79);
        Distance distance = new Distance(100, Metrics.KILOMETERS);
        List<Address2d> personList = addressRepository.findByLocationNear(point, distance);
        System.out.println(">>>"+personList);


    }
}
