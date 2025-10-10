package com.free.scheduling.api;

import com.free.scheduling.api.base.RpcResponse;
import com.free.scheduling.api.base.SchedulingUpdateRpcRequest;

/**
 * @author funanbing
 * @date 2025-09-26 11:31:21
 * @description
 */
public interface SchedulingUpdateRpcService {

    /**
     * 更新调度
     * @author funanbing
     * @param request
     * @return
     */
    RpcResponse<Boolean> updateScheduling(SchedulingUpdateRpcRequest request);

}
