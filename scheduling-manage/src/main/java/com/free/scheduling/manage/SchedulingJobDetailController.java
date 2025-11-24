package com.free.scheduling.manage;

import com.free.scheduling.biz.service.SchedulingJobDetailReadService;
import com.free.scheduling.biz.service.SchedulingJobDetailUpdateService;
import com.free.scheduling.biz.service.base.ResponseEntity;
import com.free.scheduling.biz.service.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
@RequestMapping("/scheduling/job/detail")
public class SchedulingJobDetailController {

   private final SchedulingJobDetailReadService schedulingJobReadService;
   private final SchedulingJobDetailUpdateService schedulingJobUpdateService;

    public SchedulingJobDetailController(SchedulingJobDetailReadService schedulingJobReadService,
                                         SchedulingJobDetailUpdateService schedulingJobUpdateService) {
        this.schedulingJobReadService = schedulingJobReadService;
        this.schedulingJobUpdateService = schedulingJobUpdateService;
    }

    /**
     * 创建任务
     * @param param
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Boolean> createJob(@Valid @RequestBody CreateJobDetailParam param) {
        return schedulingJobUpdateService.createJobDetail(param);
    }

    /**
     * 分页查询任务列表
     * @param param
     * @return
     */
    @PostMapping("/page")
    public ResponseEntity<Page<JobDetailPageDTO>> page(@RequestBody JobDetailPageParam param) {
        return schedulingJobReadService.page(param);
    }

    /**
     * 修改任务
     * @param param
     * @return
     */
    @PostMapping("/updateStatus")
    public ResponseEntity<Boolean> updateStatus(@Valid @RequestBody UpdateStatusParam param) {
        return schedulingJobUpdateService.updateStatus(param);
    }
}
