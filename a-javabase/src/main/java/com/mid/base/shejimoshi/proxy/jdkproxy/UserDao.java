package com.mid.base.shejimoshi.proxy.jdkproxy;

//目标对象需要实现的接口
public interface UserDao {

    void findUserByUserCode();
    void findUserByEmai();
    
}
