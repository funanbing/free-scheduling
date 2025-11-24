package com.free.scheduling.core.rpc;

import com.free.scheduling.api.SchedulingRegisterService;
import com.free.scheduling.api.base.RpcResponse;
import com.free.scheduling.api.dto.RegisterJobRpcRequest;
import com.free.scheduling.api.dto.RegisterJobRpcResponse;
import com.free.scheduling.core.SchedulingTriggerService;
import com.free.scheduling.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author funanbing
 * @date 2025-10-10 10:26:24
 * @description
 */
@DubboService
@RequiredArgsConstructor
public class SchedulingRegisterServiceImpl implements SchedulingRegisterService {

    private final SchedulingTriggerService schedulingTriggerService;

    @Override
    public RpcResponse<RegisterJobRpcResponse> register(RegisterJobRpcRequest request) {
        boolean registerJob = schedulingTriggerService.registerJob(request);
        return registerJob ?
                RpcResponse.success(RegisterJobRpcResponse.builder().jobInfoId(request.getJobInfoId()).build()) :
                RpcResponse.fail(500, "register job fail");
    }
}
