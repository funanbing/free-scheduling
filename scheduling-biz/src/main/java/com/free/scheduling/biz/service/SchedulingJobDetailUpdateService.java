package com.free.scheduling.biz.service;

import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.CreateJobDetailParam;
import com.free.scheduling.biz.service.dto.UpdateStatusParam;

/**
 * @author funanbing
 * @date 2025-11-17 14:07:28
 * @description
 */
public interface SchedulingJobDetailUpdateService {


    /**
     * 创建任务明细
     * @param param
     * @return
     */
    ResponseEntity<Boolean> createJobDetail(CreateJobDetailParam param);

    /**
     * 修改任务状态
     * @param param
     * @return
     */
    ResponseEntity<Boolean> updateStatus(UpdateStatusParam param);

    Long registerJobDetail(CreateJobDetailParam request);
}
