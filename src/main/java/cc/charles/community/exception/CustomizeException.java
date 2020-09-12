package cc.charles.community.exception;

/**
 * 自定义异常，继承RuntimeException
 *
 * @author charlesdong
 * @version 1.0
 * @cLassName CustomizeException
 * @description
 * @date 2019/12/8 下午10:19
 * @since 1.8
 */
public class CustomizeException extends RuntimeException {

    /**
     * 错误信息
     */
    private String message;

    /**
     *  错误码
     */
    private Integer code;

    public CustomizeException(String message) {
        super(message);
        this.message = message;
    }

    public CustomizeException(MyErrorCode myErrorCode) {
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
