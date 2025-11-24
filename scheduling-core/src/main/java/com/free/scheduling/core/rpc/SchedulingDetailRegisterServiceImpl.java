package com.free.scheduling.core.rpc;

import com.free.scheduling.api.SchedulingDetailRegisterService;
import com.free.scheduling.api.base.RpcResponse;
import com.free.scheduling.api.dto.RegisterJobDetailRpcRequest;
import com.free.scheduling.api.dto.RegisterJobDetailRpcResponse;
import com.free.scheduling.core.SchedulingTriggerService;
import com.free.scheduling.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author funanbing
 * @date 2025-10-10 11:28:04
 * @description
 */
@DubboService
@RequiredArgsConstructor
public class SchedulingDetailRegisterServiceImpl implements SchedulingDetailRegisterService {


    private final SchedulingTriggerService schedulingTriggerService;

    @Override
    public RpcResponse<RegisterJobDetailRpcResponse> register(RegisterJobDetailRpcRequest request) {
        boolean exec = schedulingTriggerService.registerJobDetail(request);
        return exec ?
                RpcResponse.success(RegisterJobDetailRpcResponse.builder().build()) :
                RpcResponse.fail(500, "register job detail fail");
    }
}
