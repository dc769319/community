package cc.charles.community.service;

import cc.charles.community.dto.CommentDTO;
import cc.charles.community.enums.CommentTypeEnum;
import cc.charles.community.enums.NotificationStatusEnum;
import cc.charles.community.enums.NotificationTypeEnum;
import cc.charles.community.exception.CustomizeErrorCode;
import cc.charles.community.exception.CustomizeJsonException;
import cc.charles.community.mapper.*;
import cc.charles.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName CommentService
 * @description
 * @date 2019/12/29 下午8:34
 * @since 1.8
 */
@Service
public class CommentService {

    private CommentMapper commentMapper;

    private QuestionMapper questionMapper;

    private QuestionExtMapper questionExtMapper;

    private UserMapper userMapper;

    private CommentExtMapper commentExtMapper;

    private NotificationMapper notificationMapper;

    private UnReadCountService unReadCountService;

    @Autowired
    public CommentService(
            CommentMapper commentMapper,
            QuestionMapper questionMapper,
            QuestionExtMapper questionExtMapper,
            UserMapper userMapper,
            CommentExtMapper commentExtMapper,
            NotificationMapper notificationMapper,
            UnReadCountService unReadCountService
    ) {
        this.commentMapper = commentMapper;
        this.questionMapper = questionMapper;
        this.questionExtMapper = questionExtMapper;
        this.userMapper = userMapper;
        this.commentExtMapper = commentExtMapper;
        this.notificationMapper = notificationMapper;
        this.unReadCountService = unReadCountService;
    }

    public void insert(Comment comment, User user) {
        //验证参数是否有误
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeJsonException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        Byte commentType = comment.getType();
        if (commentType == null || !CommentTypeEnum.exist(commentType)) {
            throw new CustomizeJsonException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        //判断回复的对象是问题还是评论
        if (CommentTypeEnum.COMMENT.getType().equals(commentType)) {
            //回复的是评论
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (null == parentComment) {
                throw new CustomizeJsonException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //新增一级评论的评论数
            Integer curCommentCount = parentComment.getCommentCount();
            if (null == curCommentCount || curCommentCount < 1) {
                parentComment.setCommentCount(1);
                commentMapper.updateByPrimaryKey(parentComment);
            } else {
                parentComment.setCommentCount(1);
                commentExtMapper.incCommentCount(parentComment);
            }
            //回复的问题
            Question question = questionMapper.selectByPrimaryKey(parentComment.getParentId());
            if (null == question) {
                throw new CustomizeJsonException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //增加通知
            createNotify(comment, parentComment.getObserver(), NotificationTypeEnum.REPLY_COMMENT, user.getName(), question.getTitle());
            unReadCountService.refresh(parentComment.getObserver());
        } else {
            //回复的问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (null == question) {
                throw new CustomizeJsonException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            Integer curCommentCount = question.getCommentCount();
            if (null == curCommentCount || curCommentCount < 1) {
                question.setCommentCount(1);
                questionMapper.updateByPrimaryKey(question);
            } else {
                //设置要增加的阅读数
                question.setCommentCount(1);
                questionExtMapper.incCommentCount(question);
            }
            //增加通知
            createNotify(comment, question.getCreator(), NotificationTypeEnum.REPLY_QUESTION, user.getName(), question.getTitle());
            unReadCountService.refresh(question.getCreator());
        }
    }

    /**
     * 添加通知
     *
     * @param comment          评论
     * @param receiver         接收通知的用户id
     * @param notificationType 通知类型
     */
    private void createNotify(Comment comment, Integer receiver, NotificationTypeEnum notificationType, String notifierName, String outerTitle) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setNotifier(comment.getObserver());
        notification.setReceiver(receiver);
        notification.setOuterId(comment.getParentId());
        notification.setType(notificationType.getType());
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    /**
     * 获取问题下的评论列表
     *
     * @param qusId 问题id
     * @return 评论列表
     */
    public List<CommentDTO> listByTargetId(Integer qusId, CommentTypeEnum typeEnum) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(qusId)
                .andTypeEqualTo(typeEnum.getType());
        //按创建时间倒序
        commentExample.setOrderByClause("`gmt_create` desc");
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        if (commentList.size() == 0) {
            return new ArrayList<>();
        }
        //循环得到userId
        List<Integer> userIdList = commentList.stream().map(Comment::getObserver).distinct().collect(Collectors.toList());
        /*List<Integer> userIdList = new ArrayList<>();
        for (Comment comment : commentList) {
            Integer userId = comment.getObserver();
            if (userIdList.contains(userId)) {
                continue;
            }
            userIdList.add(userId);
        }*/
        //查询用户信息列表
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIdList);
        List<User> userList = userMapper.selectByExample(userExample);

        //构造用户信息Map
        Map<Integer, User> userMap = userList.stream().collect(Collectors.toMap(User::getId, user -> user));
        /*Map<Integer, User> userMap = new HashMap<>();
        for (User user : userList) {
            userMap.put(user.getId(), user);
        }*/

        //组装结果数据
        return commentList.stream().map((comment) -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            if (userMap.containsKey(comment.getObserver())) {
                commentDTO.setUser(userMap.get(comment.getObserver()));
            }
            return commentDTO;
        }).collect(Collectors.toList());
        /*List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            if (userMap.containsKey(comment.getObserver())) {
                commentDTO.setUser(userMap.get(comment.getObserver()));
            }
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;*/
    }
}
