package com.mid.base.shejimoshi.proxy.jdkproxy;

import java.lang.reflect.Proxy;

//代理对象生成工厂
public class UserDaoImplProxyFactory {

    public UserDao getUserDaoImpl(UserDao target){

        //创建代理对象
        UserDao proxy = (UserDao) Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{UserDao.class}, (proxy1, method, args) -> {
            //调用代理对象方法是执行
            Object result;
            if(method.getName().equals("findUserByUserCode")){
                System.out.println("open transaction");
                result = method.invoke(target,args);
                System.out.println("close transaction");
            }else{
                result = method.invoke(target,args);
            }
            return result;
        });
        return proxy;
    }

}
