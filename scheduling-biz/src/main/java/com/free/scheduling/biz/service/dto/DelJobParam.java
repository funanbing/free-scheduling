package com.free.scheduling.biz.service.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author funanbing
 * @date 2025-11-14 13:51:44
 * @description
 */
public class DelJobParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 8753616955883930021L;

    private String id;

    private String jobCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }
}
