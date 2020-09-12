package cc.charles.community.dto;

import lombok.Data;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName CommentCreateDTO
 * @description
 * @date 2019/12/28 下午11:26
 * @since 1.8
 */
@Data
public class CommentCreateDTO {
    private String content;
    private Integer parentId;
    private Byte type;
}
