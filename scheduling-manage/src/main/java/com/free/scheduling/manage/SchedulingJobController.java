package com.free.scheduling.manage;

import com.free.scheduling.biz.service.SchedulingJobReadService;
import com.free.scheduling.biz.service.SchedulingJobUpdateService;
import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author funanbing
 * @date 2025-11-14 14:01:39
 * @description
 */
@RestController
@RequestMapping("/scheduling/job")
public class SchedulingJobController {

    private final SchedulingJobUpdateService schedulingJobUpdateService;
    private final SchedulingJobReadService schedulingJobReadService;

    public SchedulingJobController(SchedulingJobUpdateService schedulingJobUpdateService,
                                   SchedulingJobReadService schedulingJobReadService) {
        this.schedulingJobUpdateService = schedulingJobUpdateService;
        this.schedulingJobReadService = schedulingJobReadService;
    }

    /**
     * 创建任务
     * @param param
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Boolean> createJob(@Valid @RequestBody CreateJobParam param) {
        return schedulingJobUpdateService.createJob(param);
    }

    /**
     * 分页查询任务列表
     * @param param
     * @return
     */
    @PostMapping("/page")
    public ResponseEntity<Page<JobPageDTO>> page(@RequestBody JobPageParam param) {
        return schedulingJobReadService.page(param);
    }

    /**
     * 删除任务
     * @param param
     * @return
     */
    @PostMapping("/del")
    public ResponseEntity<Boolean> deleteJob(@Valid @RequestBody DelJobParam param) {
        return schedulingJobUpdateService.deleteJob(param);
    }

    /**
     * 修改任务
     * @param param
     * @return
     */
    @PostMapping("/modify")
    public ResponseEntity<Boolean> modifyJob(@Valid @RequestBody ModifyJobParam param) {
        return schedulingJobUpdateService.modifyJob(param);
    }

    @GetMapping("/select")
    public ResponseEntity<List<JobSelectDTO>> selectOptions(@RequestParam(value = "jobName",required = false) String jobName) {
        return schedulingJobReadService.selectOptions(jobName);
    }
}
