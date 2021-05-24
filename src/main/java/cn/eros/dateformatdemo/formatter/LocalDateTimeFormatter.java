package cn.eros.dateformatdemo.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author 周光兵
 * @date 2021/5/22 14:06
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
    private final DateTimeFormatter dateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public LocalDateTime parse(String s, Locale locale) throws ParseException {
        return LocalDateTime.parse(s, dateTimeFormatter);
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.format(dateTimeFormatter);
    }
}
