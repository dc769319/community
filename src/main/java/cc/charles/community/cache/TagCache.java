package cc.charles.community.cache;

import cc.charles.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName TagCache
 * @description
 * @date 2020/3/29 下午1:37
 * @since 1.8
 */
@Component
public class TagCache {

    /**
     * @return 二维标签列表
     */
    public List<TagDTO> get() {
        List<TagDTO> cateList = new ArrayList<>();

        TagDTO frontend = new TagDTO();
        frontend.setCateName("前端");
        frontend.setTags(Arrays.asList("javascript", "前端", "vue.js", "css", "html", "html5", "node.js", "react.js", "jquery", "css3", "es6", "typescript", "chrome", "npm", "bootstrap", "sass", "less", "chrome-devtools", "firefox", "angular", "coffeescript", "safari", "postcss", "postman", "fiddler", "yarn", "webkit", "firebug", "edge"));
        cateList.add(frontend);

        TagDTO backend = new TagDTO();
        backend.setCateName("后端");
        backend.setTags(Arrays.asList("php", "java", "node.js", "python", "cpp", "c", "golang", "spring", "后端", "springboot", "django", "flask", "c#", "swoole", "ruby", "asp.net", "ruby-on-rails", "scala", "rust", "lavarel", "爬虫"));
        cateList.add(backend);

        return cateList;
    }

    /**
     * 校验输入的标签是否合法
     * @param tags 标签字符串
     * @return 不合法的标签字符串
     */
    public String invalidCheck(String tags) {
        String[] splitTags = tags.split(",");
        List<TagDTO> tagDTOList = get();
        //得到一维数组，包含所有合法标签
        List<String> tagList = tagDTOList.stream().flatMap(tagDTO -> tagDTO.getTags().stream()).collect(Collectors.toList());
        List<String> invalidTagList = Arrays.stream(splitTags).filter(t -> !tagList.contains(t)).collect(Collectors.toList());
        return StringUtils.join(invalidTagList, ",");
    }
}