package com.free.scheduling.biz.service.impl;

import com.free.scheduling.biz.service.SchedulingJobDetailReadService;
import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.JobDetailPageDTO;
import com.free.scheduling.biz.service.dto.JobDetailPageParam;
import com.free.scheduling.dao.entity.JobDetail;
import com.free.scheduling.dao.repository.JobDetailRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author funanbing
 * @date 2025-11-17 14:21:10
 * @description
 */
@Service
public class SchedulingJobDetailReadServiceImpl implements SchedulingJobDetailReadService {

    private final JobDetailRepository jobDetailRepository;

    @Autowired
    public SchedulingJobDetailReadServiceImpl(JobDetailRepository jobDetailRepository) {
        this.jobDetailRepository = jobDetailRepository;
    }

    @Override
    public ResponseEntity<Page<JobDetailPageDTO>> page(JobDetailPageParam param) {
        Pageable pageable = PageRequest.of(param.getPage(), param.getSize(), Sort.by(Sort.Direction.DESC, "id"));

        Specification<JobDetail> spec = (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("deleted"), 0));
            if (StringUtils.hasText(param.getJobInfoId())) {
                predicates.add(criteriaBuilder.equal(root.get("jobInfoId"), Long.parseLong(param.getJobInfoId())));
            }
            if (StringUtils.hasText(param.getBeginExecTime()) && StringUtils.hasText(param.getEndExecTime())) {
                predicates.add(criteriaBuilder.between(root.get("executeTime"), LocalDateTime.parse(param.getBeginExecTime()),
                        LocalDateTime.parse(param.getEndExecTime())));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<JobDetail> detailPage = jobDetailRepository.findAll(spec, pageable);
        return ResponseEntity.success(detailPage.map(JobDetailPageDTO::new));
    }

    @Override
    public List<JobDetail> list(Long maxId) {
        return jobDetailRepository.list(maxId);
    }
}
