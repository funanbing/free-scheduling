package com.free.scheduling.client;

import com.free.scheduling.client.annotation.ScheduleCallBack;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author funanbing
 * @date 2025-10-10 15:52:45
 * @description
 */
@DubboService
@ScheduleCallBack(method = "callBack", extParam = "{\"name\":\"1231\"}",jobName = "gateway", interfaceName = "com.free.scheduling.client.GatewayScheduling")
public class GatewaySchedulingImpl implements GatewayScheduling {

    @Override
    public void callBack() {
        System.out.println("callBack...........");
    }
}
