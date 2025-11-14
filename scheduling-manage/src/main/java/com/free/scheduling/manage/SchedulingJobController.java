package com.free.scheduling.manage;

import com.free.scheduling.biz.service.SchedulingJobUpdateService;
import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.CreateJobParam;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author funanbing
 * @date 2025-11-14 14:01:39
 * @description
 */
@RestController
@RequestMapping("/scheduling/job")
public class SchedulingJobController {

    private final SchedulingJobUpdateService schedulingJobUpdateService;

    public SchedulingJobController(SchedulingJobUpdateService schedulingJobUpdateService) {
        this.schedulingJobUpdateService = schedulingJobUpdateService;
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createJob(@Valid @RequestBody CreateJobParam param) {
        return schedulingJobUpdateService.createJob(param);
    }

}
