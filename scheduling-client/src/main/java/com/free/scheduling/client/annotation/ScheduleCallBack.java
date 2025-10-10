package com.free.scheduling.client.annotation;

import java.lang.annotation.*;

/**
 * @author funanbing
 * @date 2025-10-09 10:11:54
 * @description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ScheduleCallBack {

    String method();

    String extParam() default "";

    String jobName();

    String interfaceName();

}
