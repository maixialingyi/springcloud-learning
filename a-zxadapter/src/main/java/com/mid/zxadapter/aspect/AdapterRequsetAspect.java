package com.mid.zxadapter.aspect;

import com.alibaba.fastjson.JSON;
import com.mid.zxadapter.api.request.AbstractRequest;
import com.mid.zxadapter.api.request.Request;
import com.mid.zxadapter.api.request.RequestFactory;
import com.mid.zxadapter.utils.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class AdapterRequsetAspect {

    @Around(value = "@annotation(com.mid.zxadapter.aspect.AdapterRequset)")
    public Object sagaFlowProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        Request request = (Request) joinPoint.getArgs()[0];
        String mapKey = request.getSupplierAlias()+"-"+request.getServiceAlias()+"-"+request.getActionAlias();
        Class c = RequestFactory.getRequestClass(mapKey);
        AbstractRequest abstractRequest = (AbstractRequest) BeanUtil.getBean(c);

        Map<String, Object> kvMapping = request.getKvMapping();

        Class cls = abstractRequest.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        for (int i=0;i<fields.length;i++){//遍历
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //设置属性值
                field.set(abstractRequest,kvMapping.get(name));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        request.setRequest(abstractRequest);
        System.out.println(JSON.toJSONString(abstractRequest));
        return null;
    }
}
