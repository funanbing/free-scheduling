package com.free.scheduling.client;

import com.free.scheduling.api.SchedulingUpdateRpcService;
import com.free.scheduling.api.base.RpcResponse;
import com.free.scheduling.api.base.SchedulingUpdateRpcRequest;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author funanbing
 * @date 2025-09-26 11:41:32
 * @description
 */
@DubboService
public class SchedulingUpdateRpcServiceImpl implements SchedulingUpdateRpcService {

    @Override
    public RpcResponse<Boolean> updateScheduling(SchedulingUpdateRpcRequest request) {
        return RpcResponse.success( true);
    }
}
