<img width="1181" height="712" alt="image" src="https://github.com/user-attachments/assets/f20b520c-da72-4ed1-a110-97f6b4bb4134" />
一个基于IOT平台实现的一个分布式调度系统，命名自由调度。
举例：
调度任务-网关状态任务
执行任务明细
    -gateway1（网关1）
    -gateway2（网关2）
    -gateway3（网关3）
将可执行的任务拆分，避免扫描所有网关设备带来统计压力。
例如订单的支付超时，在使用xxl-job通过扫全部未支付的订单来检查是否支付超时。

这样的优势在于减轻了应用服务的压力，还可以自定义任务的执行时间。

应用参考项目：free-scheduling-example

