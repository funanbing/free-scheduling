package com.free.scheduling.biz.service;

import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.JobDetailPageDTO;
import com.free.scheduling.biz.service.dto.JobDetailPageParam;
import com.free.scheduling.dao.entity.JobDetail;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author funanbing
 * @date 2025-11-17 14:20:23
 * @description
 */
public interface SchedulingJobDetailReadService {

    /**
     * 分页查询任务明细列表
     * @param param
     * @return
     */
    ResponseEntity<Page<JobDetailPageDTO>> page(JobDetailPageParam param);

    /**
     * 获取任务明细列表
     * @param maxId
     * @return
     */
    List<JobDetail> list(Long maxId);
}
