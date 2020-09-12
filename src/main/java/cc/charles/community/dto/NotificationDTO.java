package cc.charles.community.dto;

import lombok.Data;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName NotificationDTO
 * @description
 * @date 2020/4/11 下午10:33
 * @since 1.8
 */
@Data
public class NotificationDTO {
    private Integer id;
    private Integer notifier;
    private Integer receiver;
    private Byte type;
    private Byte status;
    private String typeName;
    private Long gmtCreate;
    private String notifierName;
    private String outerTitle;
}
