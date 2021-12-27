package com.mid.base;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gaohaiyang on 2018/7/24.
 */
public class BigDecimalUtil {

    /**
     * sour是否大于dest
     * @param sour
     * @param dest
     * @return
     */
    public static boolean isGreaterThan(BigDecimal sour, BigDecimal dest){
        return sour.compareTo(dest) > 0;
    }

    /**
     * sour是否等于dest
     * @param sour
     * @param dest
     * @return
     */
    public static boolean isEqualsWith(BigDecimal sour, BigDecimal dest){
        return sour.compareTo(dest) == 0;
    }

    /**
     * sour是否小于dest
     * @param sour
     * @param dest
     * @return
     */
    public static boolean isLessThan(BigDecimal sour, BigDecimal dest){
        return sour.compareTo(dest) < 0;
    }
    /**
     * sour是否大于等于dest
     * @param sour
     * @param dest
     * @return
     */
    public static boolean isGreaterThanAndEquals(BigDecimal sour, BigDecimal dest){
        return sour.compareTo(dest) >= 0;
    }
    /**
     * sour是否小于等于dest
     * @param sour
     * @param dest
     * @return
     */
    public static boolean isLessThanAndEquals(BigDecimal sour, BigDecimal dest){
        return sour.compareTo(dest) <= 0;
    }

    /**
     * 整除
     * @param sour
     * @param dest
     * @return
     */
    public static long divideExactly(BigDecimal sour, BigDecimal dest){
        return sour.divide(dest, 2, BigDecimal.ROUND_DOWN).longValue();
    }

    public static String toStringSafely(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        return bigDecimal.toString();
    }

    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for(int i =0;i<10000 ;i++){
            concurrentHashMap.put("jiang"+i,"shaoyue");
        }
    }
}
