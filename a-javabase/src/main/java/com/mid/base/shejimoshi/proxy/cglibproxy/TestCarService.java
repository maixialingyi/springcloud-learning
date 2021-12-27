package com.mid.base.shejimoshi.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class TestCarService {

    public static void main(String[] args) {
        //1创建目标对象
        final CarService carService=new CarService();
        //2创建代理对象
        //2.1创建增强器对象
        Enhancer e=new Enhancer();
        //2.2设置增强器的类加载器
        e.setClassLoader(carService.getClass().getClassLoader());
        //2.3设置代理对象父类类型
        e.setSuperclass(carService.getClass());
        //2.4设置回调函数
        e.setCallback(new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开始事务。。。");
                Object obj = method.invoke(carService, args);
                System.out.println("结束事务。。。");
                return obj;
            }
        });
        //2.5创建代理对象
        CarService proxy =(CarService) e.create();
        //3.执行代理对象业务
        proxy.save("保时捷");

    }
}
