package com.mid.base.jdkProxy.mockdubbo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 有实现类
 */
public class JDKNoHasExtendsProxy implements InvocationHandler {

    private DubboInvoker dubboInvoker;

    public JDKNoHasExtendsProxy(DubboInvoker dubboInvoker) {
        this.dubboInvoker = dubboInvoker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Do before");
        Object result = dubboInvoker.invoke();
        System.out.println("Do after");
        return result;
    }

    public static void main(String[] args) {
        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        DubboInvoker dubboInvoker = (DubboInvoker) Proxy.newProxyInstance(ClassUtils.getClassLoader(DubboInvoker.class), new Class[]{DubboInvoker.class}, new JDKNoHasExtendsProxy(new NettyDubboInvoker()));
        dubboInvoker.invoke();
    }

}
