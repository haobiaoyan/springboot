package com.hb.springboot.annotation;

import com.hb.springboot.enums.OperateType;
import com.hb.springboot.enums.OperateUnit;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Log {
    /**
     * 方法描述
     * @return
     */
    String detail() default "";

    /**
     * 日志等级
     * @return
     */
    int level() default 0;

    /**
     * 操作类型（enum）
     * @return
     */
    OperateType operateType() default OperateType.UNKNOWN;

    /**
     * 被操作的对象
     * @return
     */
    OperateUnit operateUnit() default OperateUnit.UNKNOWN;
}
