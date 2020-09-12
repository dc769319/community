package cc.charles.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName TagDTO
 * @description
 * @date 2020/3/29 下午1:35
 * @since 1.8
 */
@Data
public class TagDTO {
    private String cateName;
    private List<String> tags;
}
