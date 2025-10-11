package com.free.scheduling.client.annotation;

import com.free.scheduling.api.dto.JobCallBack;
import com.free.scheduling.client.util.JobCallBackUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author funanbing
 * @date 2025-10-10 15:21:55
 * @description
 */
@Component
public class JobCallBackHandler implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String[] namesForAnnotation = applicationContext.getBeanNamesForAnnotation(ScheduleCallBack.class);
        for (String name : namesForAnnotation) {
            Object bean = applicationContext.getBean(name);
            ScheduleCallBack scheduleCallBack = bean.getClass().getAnnotation(ScheduleCallBack.class);
            Method[] methods = bean.getClass().getDeclaredMethods();
            long count = Arrays.stream(methods).filter(m -> m.getName().equals(scheduleCallBack.method())).count();
            if (count != 1) {
                throw new RuntimeException("method count error,that more then "+count);
            }
            for (Method method : methods) {
                if (method.getName().equals(scheduleCallBack.method())) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    JobCallBackUtil.put(scheduleCallBack.jobName(), JobCallBack.builder()
                            .interfaceName(scheduleCallBack.interfaceName())
                            .method(scheduleCallBack.method())
                            .paramTypes(Arrays.stream(parameterTypes).map(Class::getName).toList().toArray(new String[0]))
                            .extParam(scheduleCallBack.extParam())
                            .build());
                    break;
                }
            }

        }
    }
}
