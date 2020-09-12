package cc.charles.community.exception;

/**
 * 自定义ErrorCode枚举
 * @author dongchao
 */
public enum CustomizeErrorCode implements MyErrorCode {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    UNKNOWN_ERROR(1000, "未知错误"),
    LOGIN_EXCEPTION(1001, "登录异常"),
    NO_LOGIN(1002, "未登录"),
    QUESTION_NOT_FOUND(1003, "问题不存在或已被删除"),
    TARGET_PARAM_NOT_FOUND(1004, "未选中任何问题或评论进行回复"),
    TYPE_PARAM_WRONG(1005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(1006, "评论不存在或已被删除"),
    COMMENT_CONTENT_EMPTY(1007, "评论内容为空"),
    NOTIFICATION_NOT_FOUND(1008, "通知内容不存在");

    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }
}
