package cn.eros.dateformatdemo.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 周光兵
 * @date 2021/5/20 15:59
 */
@Data
public class DateQuery {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
}
