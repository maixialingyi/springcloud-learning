package com.mid.base.jdkProxy.hasextends;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 有实现类
 */
public class JDKHasExtendsProxy implements InvocationHandler {

    private Object target;

    public JDKHasExtendsProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取被代理接口实例对象
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Do before");
        Object result = method.invoke(target, args);
        System.out.println("Do after");
        return result;
    }

    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // jdk动态代理测试
        ISubject subject = new JDKHasExtendsProxy(new RealSubject()).getProxy();
        subject.pull();
        subject.push();
    }
}
