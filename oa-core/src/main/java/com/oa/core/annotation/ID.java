package com.oa.core.annotation;

import java.lang.annotation.*;


@Documented //文档
@Retention(RetentionPolicy.RUNTIME) //在运行时可以获取
@Target({ElementType.FIELD}) //作用到类，方法，接口上等
@Inherited //子类会继承
public @interface ID {
    public String name() default "";
}
