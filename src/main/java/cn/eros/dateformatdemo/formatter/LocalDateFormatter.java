package cn.eros.dateformatdemo.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author 周光兵
 * @date 2021/5/22 14:04
 */
public class LocalDateFormatter implements Formatter<LocalDate> {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        return LocalDate.parse(s, dateTimeFormatter);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return localDate.format(dateTimeFormatter);
    }
}
