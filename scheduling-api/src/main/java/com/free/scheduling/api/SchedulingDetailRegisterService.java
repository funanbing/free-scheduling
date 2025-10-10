package com.free.scheduling.api;

import com.free.scheduling.api.base.RpcResponse;
import com.free.scheduling.api.dto.RegisterJobDetailRpcRequest;
import com.free.scheduling.api.dto.RegisterJobDetailRpcResponse;

/**
 * @author funanbing
 * @date 2025-10-09 10:42:15
 * @description
 */
public interface SchedulingDetailRegisterService {

    /**
     * 注册调度任务详情
     * @param request
     * @return
     */
    RpcResponse<RegisterJobDetailRpcResponse> register(RegisterJobDetailRpcRequest request);
}
