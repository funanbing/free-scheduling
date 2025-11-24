package com.free.scheduling.biz.service;

import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.JobPageDTO;
import com.free.scheduling.biz.service.dto.JobPageParam;
import com.free.scheduling.biz.service.dto.JobSelectDTO;
import com.free.scheduling.dao.entity.JobInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author funanbing
 * @date 2025-11-17 09:32:50
 * @description
 */
public interface SchedulingJobReadService {

    /**
     * 分页查询任务列表
     * @param param
     * @return
     */
    ResponseEntity<Page<JobPageDTO>> page(JobPageParam param);

    /**
     * 查询任务列表
     * @param jobName
     * @return
     */
    ResponseEntity<List<JobSelectDTO>> selectOptions(String jobName);

    JobInfo findById(Long id);
}
