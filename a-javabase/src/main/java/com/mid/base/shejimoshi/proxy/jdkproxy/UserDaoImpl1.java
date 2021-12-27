package com.mid.base.shejimoshi.proxy.jdkproxy;

//目标对象
public class UserDaoImpl1 implements UserDao {
    @Override
    public void findUserByUserCode() {
        System.out.println("目标对象1:findUserByUserCode");
    }

    @Override
    public void findUserByEmai() {
        System.out.println("目标对象1:findUserByEmai");
    }
}
