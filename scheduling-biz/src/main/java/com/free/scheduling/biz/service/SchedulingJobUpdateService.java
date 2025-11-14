package com.free.scheduling.biz.service;

import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.CreateJobParam;
import com.free.scheduling.biz.service.dto.DelJobParam;
import com.free.scheduling.biz.service.dto.ModifyJobParam;

/**
 * @author funanbing
 * @date 2025-11-14 13:40:17
 * @description
 */
public interface SchedulingJobUpdateService {

    /**
     * 创建任务
     * @param param
     * @return
     */
    ResponseEntity<Boolean> createJob(CreateJobParam param);

    /**
     * 删除任务
     * @param param
     * @return
     */
    ResponseEntity<Boolean> deleteJob(DelJobParam param);

    /**
     * 修改任务信息
     * @param param
     * @return
     */
    ResponseEntity<Boolean> modifyJob(ModifyJobParam param);


}
