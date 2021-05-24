package cn.eros.dateformatdemo.controller;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author 周光兵
 * @date 2021/5/22 13:51
 */
@Data
public class DateTimeQuery implements Serializable {
    private Date oldDate;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private LocalTime localTime;
}
