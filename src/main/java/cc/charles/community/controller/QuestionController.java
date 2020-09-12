package cc.charles.community.controller;

import cc.charles.community.dto.CommentDTO;
import cc.charles.community.dto.QuestionDTO;
import cc.charles.community.enums.CommentTypeEnum;
import cc.charles.community.model.User;
import cc.charles.community.service.CommentService;
import cc.charles.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName QuestionController
 * @description
 * @date 2019/11/4 下午10:14
 * @since 1.8
 */
@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(
            @PathVariable("id") Integer id,
            Model model
    ) {
        //根据id拿到问题详情数据
        QuestionDTO questionDTO = questionService.info(id);
        //根据id拿到评论列表
        List<CommentDTO> commentDTOList = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //阅读数加1
        questionService.incView(id);
        model.addAttribute("commentDTOList", commentDTOList);
        model.addAttribute("question", questionDTO);
        //获取相关的问题（有相同tag）
        List<QuestionDTO> questionDTOList = questionService.relatedQues(questionDTO);
        model.addAttribute("relatedQues", questionDTOList);
        return "question";
    }
}
