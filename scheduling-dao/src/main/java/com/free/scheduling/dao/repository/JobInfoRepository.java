package com.free.scheduling.dao.repository;

import com.free.scheduling.dao.entity.JobInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author funanbing
 * @date 2025-10-29 15:12:38
 * @description
 */
@Repository
public interface JobInfoRepository extends JpaRepository<JobInfo, Long>, JpaSpecificationExecutor<JobInfo> {

    /**
     * 校验任务名称或任务编码是否存在
     * @param jobName
     * @param jobCode
     * @return
     */
    @Query("select count(1) from JobInfo j where (j.jobName = ?1 or j.jobCode = ?2) and j.deleted = 0")
    int validJob(String jobName, String jobCode);

    /**
     * 根据任务名称和分页信息查询任务列表
     * @param spec
     * @param pageable
     * @return
     */
    Page<JobInfo> findAll(Specification<JobInfo> spec, Pageable pageable);
    
    /**
     * 根据ID查询任务信息
     * @param id
     * @return
     */
    @Query("select j from JobInfo j where j.id = :id and j.deleted = 0")
    Optional<JobInfo> findById(@Param("id") Long id);

    /**
     * 查询任务选项
     * @param jobName
     * @return
     */
    @Query("select j from JobInfo j where j.deleted = 0 and (:jobName is null or j.jobName like concat('%',:jobName,'%'))")
    List<JobInfo> queryByName(@Param("jobName") String jobName);
}