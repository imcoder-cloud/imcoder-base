package fun.imcoder.cloud.enums;

public enum ResponseEnum {
    SUCCESS(200,"成功"),
    BAD_REQUEST(400,"Bad Request"),
    INCORRECT_PARAMS(400, "参数不正确"),
    AUTH_ERROR(400,"身份认证失败"),
    USER_NOT_FOUND(400,"用户名或密码错误"),
    INVALID_GRANT_TYPE(400,"无效的 grant_type"),
    INVALID_SCOPE(400,"无效的 scope"),
    UNAUTHORIZED(401,"访问此资源需要完全的身份验证"),
    ACCESS_TOKEN_INVALID(401,"access_token 无效"),
    REFRESH_TOKEN_INVALID(401,"refresh_token 无效"),
    INSUFFICIENT_PERMISSIONS(403,"该用户权限不足以访问该资源接口"),
    SERVER_ERROR(500, "服务器错误"),
    ;
    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
