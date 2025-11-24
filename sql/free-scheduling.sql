CREATE TABLE job_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    job_name VARCHAR(31) NOT NULL COMMENT '任务名称',
    job_code VARCHAR(31) NOT NULL COMMENT '任务编码',
    remark VARCHAR(255) COMMENT '任务描述',
    job_type TINYINT NOT NULL DEFAULT 0 COMMENT '任务类型',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '状态',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '删除标记',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB COMMENT='任务信息表';



CREATE TABLE job_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    job_info_id BIGINT COMMENT '任务主键',
    job_name VARCHAR(31) NOT NULL COMMENT '任务名称',
    job_detail_name VARCHAR(31) NOT NULL COMMENT '任务明细名称',
    job_detail_code VARCHAR(31) NOT NULL COMMENT '任务明细编码',
    execute_status TINYINT NOT NULL DEFAULT 0 COMMENT '执行状态',
    execute_time DATETIME COMMENT '执行时间',
    remark VARCHAR(255) COMMENT '任务明细描述',
    deleted TINYINT DEFAULT 0 COMMENT '删除标记',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务明细表';


##数据量大可以根据hash(job_info_id) % 100, 将数据均匀分配到100张表中
CREATE TABLE job_execute_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    job_info_id BIGINT COMMENT '任务主键',
    job_info_name VARCHAR(31) NOT NULL COMMENT '任务名称',
    job_detail_id BIGINT COMMENT '任务明细主键',
    job_detail_name VARCHAR(31) NOT NULL COMMENT '任务明细名称',
    execute_result TINYINT DEFAULT 0 COMMENT '执行结果',
    execute_time DATETIME COMMENT '执行时间',
    remark VARCHAR(255) COMMENT '任务明细描述',
    deleted TINYINT NOT NULL DEFAULT 0 COMMENT '删除标记',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务执行记录表';