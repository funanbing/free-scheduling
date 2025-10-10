package com.free.scheduling.api;

import com.free.scheduling.api.base.RpcResponse;
import com.free.scheduling.api.dto.RegisterJobRpcRequest;
import com.free.scheduling.api.dto.RegisterJobRpcResponse;

/**
 * @author funanbing
 * @date 2025-10-09 10:33:22
 * @description
 */
public interface SchedulingRegisterService {

    /**
     * 注册调度任务
     * @param request
     * @return
     */
    RpcResponse<RegisterJobRpcResponse> register(RegisterJobRpcRequest request);
}
