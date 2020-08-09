package com.mongodbdemo.utils;

import org.springframework.data.geo.Point;

import java.math.BigDecimal;
import java.util.Random;

/**
 * @author: wr
 * @description:
 * @date: 2020-08-01 14:24
 * @since:
 */
public class RandomUtils {
    /**
     * @Title: randomLonLat
     * @Description: 在矩形内随机生成经纬度
     * @param MinLon：最小经度  MaxLon： 最大经度   MinLat：最新纬度   MaxLat：最大纬度    type：设置返回经度还是纬度
     * @return
     * @throws
     */
    public static Point randomLonLat(double min1, double max1, double min2, double max2) {
        Random random = new Random();
        BigDecimal db = new BigDecimal(Math.random() * (max1 - min1) + min1);
        double lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();//小数后6位
        db = new BigDecimal(Math.random() * (max2 - min2) + min2);
        double lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        return new Point(lon,lat);
    }
}
