package cn.eros.dateformatdemo.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * 参数错误处理类
 * <p>Create time: 2020/12/15 16:01
 *
 * @author 周光兵
 */
@Slf4j
@Order(1100)
@ControllerAdvice
@ResponseBody
public class MethodArgumentExceptionHandler {
    /**
     * 方法参数校验错误处理类
     *
     * @param ex 异常信息
     * @return 响应结果
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    public String missingPathVariableExceptionHandler(MethodArgumentNotValidException ex) {
        String errorMessage = this.getErrorMessage(ex.getBindingResult());

        log.warn("参数校验未通过，原因：{}", errorMessage);

        return errorMessage;
    }

    /**
     * 参数绑定异常
     *
     * @param ex 异常信息
     * @return 响应结果
     */
    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public String missingPathVariableExceptionHandler(BindException ex) {
        String errorMessage = this.getErrorMessage(ex.getBindingResult());

        log.warn("参数绑定失败，原因：{}", errorMessage);

        return errorMessage;
    }

    private String getErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();

        List<ObjectError> errors = bindingResult.getAllErrors();
        boolean first = true;

        for (ObjectError error : errors) {
            if (!first) {
                errorMessage.append(",");
            }

            first = false;

            errorMessage.append(error.getDefaultMessage());
        }

        return errorMessage.toString();

    }
}
