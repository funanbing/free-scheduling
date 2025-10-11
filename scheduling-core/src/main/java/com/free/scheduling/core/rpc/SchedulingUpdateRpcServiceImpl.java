package com.free.scheduling.core.rpc;

import com.free.scheduling.api.SchedulingUpdateRpcService;
import com.free.scheduling.api.base.RpcResponse;
import com.free.scheduling.api.base.SchedulingUpdateRpcRequest;
import com.free.scheduling.core.SchedulingTriggerService;
import com.free.scheduling.core.enums.JobStatusEnum;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.util.Assert;

/**
 * @author funanbing
 * @date 2025-09-26 11:41:32
 * @description
 */
@DubboService
@RequiredArgsConstructor
public class SchedulingUpdateRpcServiceImpl implements SchedulingUpdateRpcService {

    private final SchedulingTriggerService schedulingTriggerService;

    @Override
    public RpcResponse<Boolean> updateScheduling(SchedulingUpdateRpcRequest request) {
        Assert.notNull(request, "update request is null");
        Assert.isTrue(StringUtils.hasText(request.getJobId()), "jobId is null");
        Assert.state(JobStatusEnum.statusMap.containsKey(request.getStatus()), "status is error");

        return RpcResponse.success( schedulingTriggerService.update(request));
    }
}
