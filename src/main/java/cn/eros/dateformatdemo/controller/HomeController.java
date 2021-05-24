package cn.eros.dateformatdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 周光兵
 * @date 2021/5/19 11:05
 */
@Api
@Slf4j
@RestController
public class HomeController {
    @GetMapping("/date")
    public DateVO getDate() {
        return new DateVO() {{
            setCol1(new Date());
            setCol2(LocalDate.now());
            setCol3(LocalDateTime.now());
        }};
    }

    @GetMapping("/query/date")
    public DateQuery queryDate(DateQuery query) {
        log.info("param: {}", query);

        return  query;
    }

    @GetMapping("/query/date1")
    public DateTimeQuery queryDate(DateTimeQuery query) {
        log.info("param: {}", query);

        return query;
    }

    @PostMapping("/query/date1")
    public DateTimeQuery postQueryDate(@RequestBody DateTimeQuery submitData) {
        log.info("param: {}", submitData);
        return submitData;
    }
}
