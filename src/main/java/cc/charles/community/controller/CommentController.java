package cc.charles.community.controller;

import cc.charles.community.dto.CommentCreateDTO;
import cc.charles.community.dto.CommentDTO;
import cc.charles.community.dto.ResultDTO;
import cc.charles.community.enums.CommentTypeEnum;
import cc.charles.community.exception.CustomizeErrorCode;
import cc.charles.community.mapper.CommentMapper;
import cc.charles.community.model.Comment;
import cc.charles.community.model.User;
import cc.charles.community.service.CommentService;
import cc.charles.community.service.UnReadCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName CommentController
 * @description
 * @date 2019/12/28 下午11:22
 * @since 1.8
 */
@Controller
public class CommentController {

    private CommentService commentService;
    private UnReadCountService unReadCountService;

    @Autowired
    public CommentController(CommentService commentService, UnReadCountService unReadCountService) {
        this.commentService = commentService;
        this.unReadCountService = unReadCountService;
    }

    /**
     * 创建一个评论
     *
     * @param commentCreateDTO 评论创建DTO对象
     * @param request          request对象
     * @return Json结果
     */
    @PostMapping(value = "/comment")
    @ResponseBody
    public ResultDTO create(
            @RequestBody CommentCreateDTO commentCreateDTO,
            HttpServletRequest request
    ) {
        User user = (User) request.getAttribute("user");
        if (null == user) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        //验证内容是否为空
        if (StringUtils.isEmpty(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.COMMENT_CONTENT_EMPTY);
        }

        Comment comment = new Comment();
        comment.setContent(commentCreateDTO.getContent());
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setType(commentCreateDTO.getType());
        comment.setObserver(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        commentService.insert(comment, user);
        return ResultDTO.success();
    }

    /**
     * 根据评论id获取二级评论列表
     *
     * @param commentId 评论id
     * @return json形式的评论列表
     */
    @GetMapping("/subComment/{commentId}")
    @ResponseBody
    public ResultDTO<List<CommentDTO>> subComment(
            @PathVariable(name = "commentId") Integer commentId
    ) {
        List<CommentDTO> subCommentList = commentService.listByTargetId(commentId, CommentTypeEnum.COMMENT);
        return ResultDTO.success(subCommentList);
    }
}
