package cn.eros.dateformatdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.beans.PropertyEditorSupport;
import java.text.Format;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * @author 周光兵
 * @date 2021/5/23 11:42
 */
@RestControllerAdvice
public class ControllerAdviceInitBinder {
    @Value("${spring.mvc.format.date-time:yyyy-MM-dd HH:mm:ss}")
    private String dateTimePattern;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);

        webDataBinder.registerCustomEditor(
            LocalDateTime.class,
            new Editor<>(
                text -> LocalDateTime.parse(text, localDateTimeFormatter),
                localDateTimeFormatter.toFormat()));

        webDataBinder.registerCustomEditor(
            Instant.class,
            new Editor<>(
                Instant::parse,
                DateTimeFormatter.ISO_INSTANT.toFormat()));

        webDataBinder.registerCustomEditor(
            LocalDate.class,
            new Editor<>(
                text -> LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE),
                DateTimeFormatter.ISO_LOCAL_DATE.toFormat()));

        webDataBinder.registerCustomEditor(
            LocalTime.class,
            new Editor<>(
                text -> LocalTime.parse(text, DateTimeFormatter.ISO_LOCAL_TIME),
                DateTimeFormatter.ISO_LOCAL_TIME.toFormat()));
    }

    private static class Editor<T> extends PropertyEditorSupport {
        private final Function<String, T> parser;
        private final Format format;

        public Editor(Function<String, T> parser, Format format) {

            this.parser = parser;
            this.format = format;
        }

        @Override
        public void setAsText(String text) {

            setValue(this.parser.apply(text));
        }

        @Override
        public String getAsText() {
            return format.format(getValue());
        }
    }
}
