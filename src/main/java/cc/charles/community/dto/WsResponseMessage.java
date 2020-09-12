package cc.charles.community.dto;

import lombok.Data;

/**
 * @author charlesdong
 * @version 1.0
 * @date 2020/6/28 下午8:32
 * @since 1.8
 */
@Data
public class WsResponseMessage<T> {
    private String responseMessage;
    private T data;
}
