package cc.charles.community.exception;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName CustomizeJsonException
 * @description
 * @date 2019/12/29 下午9:45
 * @since 1.8
 */
public class CustomizeJsonException extends RuntimeException {
    /**
     * 错误信息
     */
    private String message;

    /**
     *  错误码
     */
    private Integer code;

    public CustomizeJsonException(String message) {
        super(message);
        this.message = message;
    }

    public CustomizeJsonException(MyErrorCode myErrorCode) {
        this.message = myErrorCode.getMessage();
        this.code = myErrorCode.getCode();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
