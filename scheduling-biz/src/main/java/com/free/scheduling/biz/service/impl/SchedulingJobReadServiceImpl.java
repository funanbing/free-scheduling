package com.free.scheduling.biz.service.impl;

import com.free.schedling.common.enums.ErrorCode;
import com.free.schedling.common.exception.JobException;
import com.free.scheduling.biz.service.SchedulingJobReadService;
import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.JobPageDTO;
import com.free.scheduling.biz.service.dto.JobPageParam;
import com.free.scheduling.biz.service.dto.JobSelectDTO;
import com.free.scheduling.dao.entity.JobInfo;
import com.free.scheduling.dao.repository.JobInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author funanbing
 * @date 2025-11-17 09:40:04
 * @description
 */
@Service
public class SchedulingJobReadServiceImpl implements SchedulingJobReadService {

    private final JobInfoRepository jobInfoRepository;

    @Autowired
    public SchedulingJobReadServiceImpl(JobInfoRepository jobInfoRepository) {
        this.jobInfoRepository = jobInfoRepository;
    }

    @Override
    public ResponseEntity<Page<JobPageDTO>> page(JobPageParam param) {
        // 创建分页和排序条件
        Pageable pageable = PageRequest.of(param.getPage(), param.getSize(), Sort.by(Sort.Direction.DESC, "id"));
        
        // 构建动态查询条件
        Specification<JobInfo> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("deleted"), 0));
            // 如果任务名称不为空，则添加模糊查询条件
            if (StringUtils.hasText(param.getJobName())) {
                predicates.add(criteriaBuilder.like(root.get("jobName"), "%" + param.getJobName() + "%"));
            }
            
            // 将所有条件用AND连接
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        
        // 执行查询
        Page<JobInfo> page = jobInfoRepository.findAll(spec, pageable);
        return ResponseEntity.success(page.map(JobPageDTO::new));
    }

    @Override
    public ResponseEntity<List<JobSelectDTO>> selectOptions(String jobName) {
        List<JobInfo> jobInfos = jobInfoRepository.queryByName(jobName);
        List<JobSelectDTO> result = jobInfos.stream().map(jobInfo -> {
            JobSelectDTO dto = new JobSelectDTO();
            dto.setJobInfoId(String.valueOf(jobInfo.getId()));
            dto.setJobName(jobInfo.getJobName());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.success(result);
    }

    @Override
    public JobInfo findById(Long id) {
        return jobInfoRepository.findById(id).orElseThrow(() -> new JobException(ErrorCode.JOB_QUERY_ERROR_NULL));
    }
}