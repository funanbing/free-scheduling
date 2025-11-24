package com.free.scheduling.dao.repository;

import com.free.scheduling.dao.entity.JobDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author funanbing
 * @date 2025-11-17 13:42:36
 * @description
 */
@Repository
public interface JobDetailRepository extends JpaRepository<JobDetail, Long>, JpaSpecificationExecutor<JobDetail> {

    /**
     * 根据条件分页查询任务明细列表
     * @param spec 查询条件
     * @param pageable 分页参数
     * @return 任务明细列表
     */
    Page<JobDetail> findAll(Specification<JobDetail> spec, Pageable pageable);

    /**
     * 获取待执行的任务明细列表
     * @param maxId 当前最大ID，用于分页查询
     * @return 待执行的任务明细列表
     */
    @Query("select j from JobDetail j where j.deleted = 0 and j.executeStatus = 1 and j.id > :maxId order by j.id limit 500")
    List<JobDetail> list(@Param("maxId") Long maxId);
}