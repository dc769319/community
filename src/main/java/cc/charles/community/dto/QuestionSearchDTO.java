package cc.charles.community.dto;

import lombok.Data;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName QuestionSearchDTO
 * @description
 * @date 2020/5/17 下午4:19
 * @since 1.8
 */
@Data
public class QuestionSearchDTO {
    private Long offset;
    private Long pageSize;
    private String keyword;
}
