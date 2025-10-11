![image](https://github.com/user-attachments/assets/f20b520c-da72-4ed1-a110-97f6b4bb4134 "流程图")
# 介绍
#### 一个基于IOT平台实现的一个分布式调度系统，命名自由调度。

#### 举例： 
##### 调度任务-网关状态任务

##### 任务明细-执行任务明细，如下：

    gateway1（网关1）
    gateway2（网关2）
    gateway3（网关3）

#### 将可执行的任务拆分，避免扫描所有网关设备带来统计压力。

#### 例如订单的支付超时，在使用xxl-job通过扫全部未支付的订单来检查是否支付超时。

#### 这样的优势在于减轻了应用服务的压力，还可以自定义任务的执行时间。

#### 应用参考项目：free-scheduling-example

# 快速开始
#### 1. 创建数据库 //TODO
#### 2. 应用服务引入scheduling-client

        <dependency>
            <groupId>com.free.scheduling</groupId>
            <artifactId>scheduling-client</artifactId>
            <version>${free-scheduling-client.version}</version>
        </dependency>

#### 3.手动或者调用api创建调度任务和任务明细
#### 4.使用注解@ScheduleCallBack 注入任务回调接口，接口需要是dubbo接口。例如：

        @DubboService
        @ScheduleCallBack(jobName="gatewayStatus",method = "gatewayStatus",interfaceName = "com.free.scheduling.example.service.JobExecService")
        public class JobExecServiceImpl implements JobExecService {
        
            @Override
            public RpcResponse<JobDetailRpcDTO> gatewayStatus() {
                return RpcResponse.success(JobDetailRpcDTO.builder().next(false).build());
            }
        }

#### 5.添加任务。
##### SchedulingRegisterService：注入掉调度任务。SchedulingDetailRegisterService：注入可执行任务明细。
##### 应用服务注入：

        public RpcResponse<String> registerJob() {
            RpcResponse<RegisterJobRpcResponse> rpcResponse =
                                            schedulingRegisterService.register(RegisterJobRpcRequest.builder()
                                                                                                    .jobName("gatewayStatus")
                                                                                                    .status(1)
                                                                                                    .callback(JobCallBackUtil.get("gatewayStatus"))
                                                                                                    .build());
            if (rpcResponse.getCode() == 200) {
                RpcResponse<RegisterJobDetailRpcResponse> register =
                                            schedulingDetailRegisterService.register(RegisterJobDetailRpcRequest.builder()
                                                                                .jobId(rpcResponse.getData().getJobId())
                                                                                .execTime(System.currentTimeMillis()/1000)
                                                                                .build());
                return RpcResponse.success("jobId:"+rpcResponse.getData()+",jobDetailId:"+register.getData());
            }
            return RpcResponse.fail(500, "注册失败");
        }

##### 调度服务手动添加（TODO）
