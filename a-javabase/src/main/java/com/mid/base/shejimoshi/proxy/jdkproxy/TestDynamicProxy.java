package com.mid.base.shejimoshi.proxy.jdkproxy;

//动态代理测试类
public class TestDynamicProxy {

    public static void main (String[] args) throws InterruptedException {
        //设置系统参数,把代理对象写到磁盘 路径为com.sun.proxy
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        
        //UserDaoImpl userDaoImpl = new UserDaoImpl();
        UserDaoImpl2 userDaoImpl = new UserDaoImpl2();
        UserDao userDaoImplProxy = new UserDaoImplProxyFactory().getUserDaoImpl(userDaoImpl);

        //直接调用目标对象
        userDaoImpl.findUserByUserCode();
        
        //调用目标对象代理对象
        userDaoImplProxy.findUserByUserCode();
        userDaoImplProxy.findUserByEmai();

    }

}
