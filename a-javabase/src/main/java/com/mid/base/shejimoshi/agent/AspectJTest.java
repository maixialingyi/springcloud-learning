package com.mid.base.shejimoshi.agent;


/*@Aspect
public class AspectJTest {
    @Around(value = "execution(public methodsig)", argNames = "pjp") //execution内替换要做切面的方法签名
    public Object trace(ProceedingJoinPoint pjp) throws Throwable {
        TraceContext traceCtx = TraceContext.get(); //获取追踪上下文，上下文的初始化可以在程序入口处
        String requestId = reqCtx.getRequestId(); //获取requestId
        String sig = pjp.getSignature().toShortString(); //获取方法签名
        boolean isSuccessful = false;
        String errorMsg = "";
        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = pjp.proceed();
            isSuccessful = true;
            return result;
        } catch (Throwable t) {
            isSuccessful = false;
            errorMsg = t.getMessage();
            return result;
        } finally {
            long elapseTime = System.currentTimeMillis() - start;
            Logs.info("rid : " + requestId + ", start time: " + start + ", elapseTime: " + elapseTime + ", sig: " + sig + ", isSuccessful: " + isSuccessful + ", errorMsg: " + errorMsg  );
        }
    }

}*/
