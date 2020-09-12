package cc.charles.community.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName CustomSetting
 * @description
 * @date 2020/5/23 下午3:38
 * @since 1.8
 */
@Component
@Data
@ConfigurationProperties(value = "custom-setting")
public class CustomSetting {
    private String uploadPathDir;
    private List<String> suffixLimit;
}
