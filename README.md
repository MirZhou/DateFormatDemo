# Spring Boot 时间格式化配置

**支持`Date`、`LocalDate`、`LocalDateTime`、`LocalTime`**

配置文件`application.properties`：
``` properties
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
spring.jackson.time-zone=GMT+8
spring.jackson.serialization.write-dates-as-timestamps=false
spring.mvc.date-format=yyyy-MM-dd HH:mm:ss

spring.format.date-format=yyyy-MM-dd
spring.format.time-format=HH:mm:ss
```

相关配置类：
* `cn.eros.dateformatdemo.config.ControllerAdviceInitBinder`：用于对接收到的日期/时间类型数据进行格式化配置
* `cn.eros.dateformatdemo.config.DateFormatConfig`：用于接口返回数据的格式化配置