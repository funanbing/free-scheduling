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
        request.setJobId(StringUtils.isBlank(request.getJobId()) ? IdUtil.getId() : request.getJobId());
        boolean trigger = schedulingTriggerService.trigger(request);
        // TODO 数据入库 mysql redis
        return trigger ?
                RpcResponse.success(RegisterJobRpcResponse.builder().jobId(request.getJobId()).build()) :
                RpcResponse.fail(500, "register job fail");
    }
}
