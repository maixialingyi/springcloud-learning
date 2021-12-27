package com.mid.zxadapter.aspect;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdapterRequset {
    String spanName() default "";
}
