package cc.charles.community.controller;

import cc.charles.community.cache.TagCache;
import cc.charles.community.dto.QuestionDTO;
import cc.charles.community.dto.TagDTO;
import cc.charles.community.exception.CustomizeErrorCode;
import cc.charles.community.exception.CustomizeException;
import cc.charles.community.model.Question;
import cc.charles.community.model.User;
import cc.charles.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName PublishController
 * @description
 * @date 2019/9/14 下午9:53
 * @since 1.8
 */
@Controller
@Slf4j
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TagCache tagCache;

    @GetMapping(value = {"/publish", "/publish/"})
    public String publish(
            Model model,
            HttpServletRequest request
    ) {
        User user = (User) request.getAttribute("user");

        if (null == user) {
            throw new CustomizeException(CustomizeErrorCode.LOGIN_EXCEPTION);
        }
        List<TagDTO> tagDTOList = tagCache.get();
        model.addAttribute("tagCache", tagDTOList);
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(
            @PathVariable(name = "id") Integer id,
            Model model
    ) {
        QuestionDTO questionDTO = questionService.info(id);
        if (questionDTO == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", id);

        List<TagDTO> tagDTOList = tagCache.get();
        model.addAttribute("tagCache", tagDTOList);
        return "publish";
    }

    @PostMapping(value = {"/publish", "/publish/"})
    public String doPublish(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "tag") String tag,
            @RequestParam(name = "id", required = false) Integer id,
            Model model,
            HttpServletRequest request
    ) {
        User user = (User) request.getAttribute("user");

        if (null == user) {
            throw new CustomizeException(CustomizeErrorCode.LOGIN_EXCEPTION);
        }

        //方便数据回显
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        //验证数据是否正确
        if (title == null || "".equals(title)) {
            model.addAttribute("errMsg", "标题不能为空");
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("errMsg", "描述不能为空");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("errMsg", "标签不能为空");
            return "publish";
        }

        //校验标签是否合法
        String invalidTags = tagCache.invalidCheck(tag);
        log.info(invalidTags);
        if (!("".equals(invalidTags))) {
            model.addAttribute("errMsg", "标签不合法：" + invalidTags);
            return "publish";
        }

        //如果是编辑状态，验证当前文章是否为当前用户编写
        if (null != id) {
            QuestionDTO questionDTO = questionService.info(id);
            if (user.getId().equals(questionDTO.getUser().getId())) {
                model.addAttribute("errMsg", "非法编辑，当前内容并非当前登录用户编写");
                return "publish";
            }
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);
        if (questionService.save(question) > 0) {
            model.addAttribute("sucMsg", "操作成功");
        } else {
            model.addAttribute("errMsg", "操作失败");
        }
        return "publish";
    }
}
