package cc.charles.community.dto;

import lombok.Data;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName FileUploadDTO
 * @description
 * @date 2020/5/16 下午4:16
 * @since 1.8
 */
@Data
public class FileUploadDTO {
    private Integer success;
    private String message;
    private String url;
}
