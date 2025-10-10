package com.free.scheduling.core.generic;

import java.util.concurrent.CompletableFuture;

/**
 * @author funanbing
 * @date 2025-09-26 14:00:42
 * @description
 */
public interface SchedulingInvokeService {


    CompletableFuture<Object> invoke(String interfaceName, String methodName, String[] paramTypes, Object[] args);
}
