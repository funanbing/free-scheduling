package com.free.scheduling.core.generic.impl;

import com.free.scheduling.core.generic.SchedulingGenericService;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author funanbing
 * @date 2025-09-26 13:53:16
 * @description
 */
@Service
public class SchedulingGenericServiceImpl implements SchedulingGenericService {

    private final static Map<String, GenericService> SERVICE_MAP = new ConcurrentHashMap<>();

    @Override
    public GenericService service(String interfaceName) {
        if (!SERVICE_MAP.containsKey(interfaceName)) {
            ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
            referenceConfig.setInterface(interfaceName);
            referenceConfig.setGeneric("true");
            referenceConfig.setAsync(true);
            referenceConfig.setTimeout(3000);
            SERVICE_MAP.put(interfaceName, referenceConfig.get());
        }
        return SERVICE_MAP.get(interfaceName);
    }
}
