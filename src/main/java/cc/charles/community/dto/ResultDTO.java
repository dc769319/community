package cc.charles.community.dto;

import cc.charles.community.exception.CustomizeErrorCode;
import cc.charles.community.exception.CustomizeJsonException;
import lombok.Data;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName ResultDTO
 * @description
 * @date 2019/12/29 下午6:32
 * @since 1.8
 */
@Data
public class ResultDTO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    private T data;

    /**
     * @param code    错误码
     * @param message 错误信息
     * @return 响应结果
     */
    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorEnum) {
        return errorOf(errorEnum.getCode(), errorEnum.getMessage());
    }

    public static ResultDTO errorOf(CustomizeJsonException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResultDTO success() {
        return errorOf(CustomizeErrorCode.SUCCESS);
    }

    public static <T> ResultDTO<T> success(T data) {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.setCode(CustomizeErrorCode.SUCCESS.getCode());
        resultDTO.setMessage(CustomizeErrorCode.SUCCESS.getMessage());
        resultDTO.setData(data);
        return resultDTO;
    }
}
