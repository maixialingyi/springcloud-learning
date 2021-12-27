package com.mid.base.shejimoshi.proxy.jdkproxy;

//目标对象
public class UserDaoImpl2 implements UserDao {
    @Override
    public void findUserByUserCode() {
        System.out.println("目标对象2:findUserByUserCode");
    }

    @Override
    public void findUserByEmai() {
        System.out.println("目标对象2:findUserByEmai");
    }

    public void weishixianjikouMethod(){
        System.out.println("未实现接口的方法,用接口调用肯定是调用不到的");
    }
}
