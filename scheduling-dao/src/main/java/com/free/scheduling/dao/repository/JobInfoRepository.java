package com.free.scheduling.dao.repository;

import com.free.scheduling.dao.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author funanbing
 * @date 2025-10-29 15:12:38
 * @description
 */
@Repository
public interface JobInfoRepository extends JpaRepository<JobInfo, Long>, JpaSpecificationExecutor<JobInfo> {

    @Query("select count(1) from JobInfo j where j.jobName = ?1 or j.jobCode = ?2")
    int validJob(String jobName, String jobCode);

}