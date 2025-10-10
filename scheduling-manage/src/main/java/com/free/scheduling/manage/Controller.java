package com.free.scheduling.manage;

import com.free.scheduling.api.SchedulingDetailRegisterService;
import com.free.scheduling.api.SchedulingRegisterService;
import com.free.scheduling.api.base.RpcResponse;
import com.free.scheduling.api.base.SchedulingUpdateRpcRequest;
import com.free.scheduling.api.dto.JobCallBack;
import com.free.scheduling.api.dto.RegisterJobDetailRpcRequest;
import com.free.scheduling.api.dto.RegisterJobRpcRequest;
import com.free.scheduling.api.dto.RegisterJobRpcResponse;
import com.free.scheduling.client.util.JobCallBackUtil;
import com.free.scheduling.core.generic.SchedulingInvokeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author funanbing
 * @date 2025-09-26 14:10:51
 * @description
 */
@RestController
@RequestMapping("test")
public class Controller {

    private final SchedulingRegisterService schedulingRegisterService;
    private final SchedulingDetailRegisterService schedulingDetailRegisterService;

    public Controller(SchedulingRegisterService schedulingRegisterService,
                      SchedulingDetailRegisterService schedulingDetailRegisterService) {
        this.schedulingRegisterService = schedulingRegisterService;
        this.schedulingDetailRegisterService = schedulingDetailRegisterService;
    }

    @GetMapping("/invoke")
    public Object invoke() {
        RpcResponse<RegisterJobRpcResponse> rpcResponse =
                schedulingRegisterService.register(RegisterJobRpcRequest.builder()
                        .jobName("gateway")
                        .status(1)
                        .callback(JobCallBackUtil.get("gateway"))
                        .build());
        if (rpcResponse.getCode() == 200) {
            schedulingDetailRegisterService.register(RegisterJobDetailRpcRequest.builder()
                    .jobId(rpcResponse.getData().getJobId())
                            .execTime(System.currentTimeMillis()/1000 +20)
                    .build());
        }
        return "success";
    }
}
