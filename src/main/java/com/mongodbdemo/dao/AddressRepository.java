package com.mongodbdemo.dao;

import com.mongodbdemo.entity.Address;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author: wr
 * @description:
 * @date: 2020-08-01 12:40
 * @since:
 */
public interface AddressRepository extends MongoRepository<Address,String> {


    GeoResults<Address> findByLocationNear(Point location, Distance distance);
}
