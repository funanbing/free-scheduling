package com.free.scheduling.dao.repository;

import com.free.scheduling.dao.entity.JobExecuteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author funanbing
 * @date 2025-11-17 13:42:36
 * @description
 */
@Repository
public interface JobExecuteResultRepository extends JpaRepository<JobExecuteRecord, Long>, JpaSpecificationExecutor<JobExecuteRecord> {


}
