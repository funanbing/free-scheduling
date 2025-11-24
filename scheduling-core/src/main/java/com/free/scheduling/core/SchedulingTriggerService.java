package com.free.scheduling.core;

import com.free.scheduling.api.base.SchedulingUpdateRpcRequest;
import com.free.scheduling.api.dto.RegisterJobDetailRpcRequest;
import com.free.scheduling.api.dto.RegisterJobRpcRequest;

/**
 * @author funanbing
 * @date 2025-10-09 15:12:22
 * @description
 */
public interface SchedulingTriggerService {

    /**
     * 触发任务
     * @param request
     * @return
     */
    boolean registerJob(RegisterJobRpcRequest request);

    /**
     * 执行任务
     * @param request
     * @return
     */
    boolean registerJobDetail(RegisterJobDetailRpcRequest request);
    /**
     * 更新任务
     * @param request
     * @return
     */
    boolean update(SchedulingUpdateRpcRequest request);

}
