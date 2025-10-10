package com.free.scheduling.core.generic;

import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author funanbing
 * @date 2025-09-26 13:48:12
 * @description
 */
public interface SchedulingGenericService {

    /**
     * 获取服务
     * @param interfaceName
     * @return
     */
    GenericService service(String interfaceName);

}
