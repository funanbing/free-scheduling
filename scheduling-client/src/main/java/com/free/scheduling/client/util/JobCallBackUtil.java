package com.free.scheduling.client.util;

import com.free.scheduling.api.dto.JobCallBack;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author funanbing
 * @date 2025-10-10 16:30:40
 * @description
 */
public class JobCallBackUtil {

    public final static Map<String, JobCallBack> jobCallBackMap = new ConcurrentHashMap<>();

    public static void put(String jobName, JobCallBack jobCallBack) {
        jobCallBackMap.putIfAbsent(jobName, jobCallBack);
    }

    public static JobCallBack get(String jobName) {
        return jobCallBackMap.get(jobName);
    }
}
