package cn.eros.dateformatdemo.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 周光兵
 * @date 2021/5/19 11:06
 */
@Data
public class DateVO {
    private Date col1;
    private LocalDate col2;
    private LocalDateTime col3;
}
