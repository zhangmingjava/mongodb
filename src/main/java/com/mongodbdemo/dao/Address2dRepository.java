package com.mongodbdemo.dao;

import com.mongodbdemo.entity.Address2d;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author: wr
 * @description:
 * @date: 2020-08-01 12:40
 * @since:
 */
public interface Address2dRepository extends MongoRepository<Address2d,String> {


    List<Address2d> findByLocationNear(Point location, Distance distance);
}
