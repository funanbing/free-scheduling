package com.free.scheduling.core.generic.impl;

import com.free.scheduling.core.generic.SchedulingGenericService;
import com.free.scheduling.core.generic.SchedulingInvokeService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.CompletableFuture;

/**
 * @author funanbing
 * @date 2025-09-26 14:02:29
 * @description
 */
@Service
@RequiredArgsConstructor
public class SchedulingInvokeServiceImpl implements SchedulingInvokeService {

    private final SchedulingGenericService schedulingGenericService;

    @Override
    public CompletableFuture<Object> invoke(String interfaceName, String methodName,String[] paramTypes, Object[] args) {
        GenericService service = schedulingGenericService.service(interfaceName);
        Assert.notNull(service, "service is null");

        CompletableFuture<Object> future = service.$invokeAsync(methodName, paramTypes, args);
        try {
            return future;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
