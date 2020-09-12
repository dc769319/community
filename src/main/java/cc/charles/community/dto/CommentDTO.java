package cc.charles.community.dto;

import cc.charles.community.model.User;
import lombok.Data;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName CommentDTO
 * @description
 * @date 2020/1/30 上午11:41
 * @since 1.8
 */
@Data
public class CommentDTO {
    private Integer id;
    private String content;
    private Integer parentId;
    private Byte type;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer observer;
    private Integer commentCount;
    private User user;
}
