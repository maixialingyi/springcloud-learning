package com.mid.base.shejimoshi.flyweight;


import java.util.HashMap;
import java.util.Map;

/**
 * 如果对象已经存在于享元池中，则不会再创建该对象了，而是共用享元池中内部数据一致的对象。
 * 这样就减少了对象的创建，同时也节省了同样内部数据的对象所占用的内存空间。
 *
 * 适用场景
 * 例如 Java 的 String 字符串，在一些字符串常量中，会共享常量池中字符串对象，从而减少重复创建相同值对象，占用内存空间
 * 线程池就是享元模式的一种实现
 * 将商品存储在应用服务的缓存中，那么每当用户获取商品信息时，则不需要每次都从 redis 缓存或者数据库中获取商品信息，并在内存中重复创建商品信息了。
 */
//享元工厂类
public class FlyweightFactory {
    private static final Map<String, Flyweight> FLYWEIGHT_MAP = new HashMap<>();//享元池，用来存储享元对象

    public static Flyweight getFlyweight(String type) {
        if (FLYWEIGHT_MAP.containsKey(type)) {//如果在享元池中存在对象，则直接获取
            return FLYWEIGHT_MAP.get(type);
        } else {//在响应池不存在，则新创建对象，并放入到享元池
            ConcreteFlyweight flyweight = new ConcreteFlyweight(type);
            FLYWEIGHT_MAP.put(type, flyweight);
            return flyweight;
        }
    }

    public static void main(String[] args) {
        Flyweight fw0 = FlyweightFactory.getFlyweight("a");
        Flyweight fw1 = FlyweightFactory.getFlyweight("b");
        Flyweight fw2 = FlyweightFactory.getFlyweight("a");
        Flyweight fw3 = FlyweightFactory.getFlyweight("b");
        fw1.operation("abc");
        System.out.printf("[结果(对象对比)] - [%s]\n", fw0 == fw2);
        System.out.printf("[结果(内在状态)] - [%s]\n", fw1.getType());
    }
}
