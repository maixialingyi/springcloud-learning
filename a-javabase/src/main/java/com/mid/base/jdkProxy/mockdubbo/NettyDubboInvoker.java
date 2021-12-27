package com.mid.base.jdkProxy.mockdubbo;

public class NettyDubboInvoker implements DubboInvoker {
    @Override
    public String invoke() {
        System.out.println("执行传入对象");
        return "响应";
    }
}
