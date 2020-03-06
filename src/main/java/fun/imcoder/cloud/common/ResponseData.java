package fun.imcoder.cloud.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import fun.imcoder.cloud.enums.ResponseEnum;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> implements java.io.Serializable {

    private Integer code;
    private String message;
    private T data;

    public ResponseData() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseData(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public ResponseData(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public ResponseData(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public ResponseData(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

    public static ResponseData success() {
        return new ResponseData(ResponseEnum.SUCCESS);
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<T>(ResponseEnum.SUCCESS, data);
    }

    public static <T> ResponseData<T> success(int code, String msg) {
        return new ResponseData<T>(code, msg);
    }

    public static ResponseData error(int code, String msg) {
        return new ResponseData(code, msg);
    }

    public static ResponseData error(ResponseEnum responseEnum, String msg) {
        return new ResponseData(responseEnum.getCode(), msg);
    }

    public static ResponseData error(ResponseEnum responseEnum) {
        return new ResponseData(responseEnum);
    }

    public static ResponseData error(ResponseEnum responseEnum, Object data) {
        return new ResponseData<Object>(responseEnum, data);
    }

    public static ResponseData error(org.springframework.validation.BindingResult result, org.springframework.context.MessageSource messageSource) {
        StringBuffer msg = new StringBuffer();
        //获取错误字段集合
        java.util.List<FieldError> fieldErrors = result.getFieldErrors();
        //获取本地locale,zh_CN
        java.util.Locale currentLocale = LocaleContextHolder.getLocale();
        //遍历错误字段获取错误消息
        for (FieldError fieldError : fieldErrors) {
            //获取错误信息
            String errorMessage = messageSource.getMessage(fieldError, currentLocale);
            //添加到错误消息集合内
            msg.append(fieldError.getField() + "：" + errorMessage + " , ");
        }
        return ResponseData.error(ResponseEnum.INCORRECT_PARAMS, msg.toString());
    }
}
