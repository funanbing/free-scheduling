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
        request.setJobDetailId(StringUtils.isBlank(request.getJobDetailId()) ? IdUtil.getId() : request.getJobDetailId());
        boolean exec = schedulingTriggerService.exec(request);
        // TODO 数据入库 mysql redis
        return exec ?
                RpcResponse.success(RegisterJobDetailRpcResponse.builder().jobDetailId(request.getJobDetailId()).build()) :
                RpcResponse.fail(500, "register job detail fail");
    }
}
