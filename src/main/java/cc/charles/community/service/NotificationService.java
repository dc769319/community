package cc.charles.community.service;

import cc.charles.community.dto.NotificationDTO;
import cc.charles.community.dto.PaginationDTO;
import cc.charles.community.enums.NotificationStatusEnum;
import cc.charles.community.enums.NotificationTypeEnum;
import cc.charles.community.exception.CustomizeErrorCode;
import cc.charles.community.exception.CustomizeException;
import cc.charles.community.mapper.CommentMapper;
import cc.charles.community.mapper.NotificationExtMapper;
import cc.charles.community.mapper.NotificationMapper;
import cc.charles.community.model.Comment;
import cc.charles.community.model.Notification;
import cc.charles.community.model.NotificationExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName NotificationService
 * @description
 * @date 2020/4/11 下午10:31
 * @since 1.8
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private NotificationExtMapper notificationExtMapper;

    @Autowired
    private CommentMapper commentMapper;

    private ArrayList<NotificationDTO> notificationDTOList;

    /**
     * 分类对象
     */
    private PaginationDTO paginationDTO;

    public ArrayList<NotificationDTO> getNotificationDTOList() {
        return notificationDTOList;
    }

    public PaginationDTO getPaginationDTO() {
        return paginationDTO;
    }

    /**
     * 初始化数据列表
     *
     * @param page     当前页码
     * @param pageSize 每页展示的记录条数
     * @param userId   用户id
     */
    public void initList(Long page, Long pageSize, Integer userId) {
        notificationDTOList = new ArrayList<>();
        //计算总记录条数
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Long totalCount = notificationMapper.countByExample(notificationExample);

        if (totalCount >= 1) {
            //初始化分页类
            paginationDTO = new PaginationDTO(page, pageSize, totalCount);
            //得到数据偏移量
            Long offset = paginationDTO.getOffset();
            //得到每页展示的记录条数
            Long limit = paginationDTO.getLimit();
            //获取当前页的通知列表数据
            List<Notification> notificationList = notificationExtMapper.listByReceiver(offset, limit, userId);
            for (Notification notification : notificationList) {
                NotificationDTO notificationDTO = new NotificationDTO();
                BeanUtils.copyProperties(notification, notificationDTO);
                String typeName = NotificationTypeEnum.getNameByType(notification.getType());
                notificationDTO.setTypeName(typeName);
                notificationDTOList.add(notificationDTO);
            }
        }
    }

    /**
     * 计算未读数量
     *
     * @param userId 用户编号
     * @return 未读数量
     */
    public Long unReadCount(Integer userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    /**
     * 将通知设置为已读
     *
     * @param id     消息id
     * @param userId 用户id
     * @return 消息详情
     */
    public Integer read(Integer id, Integer userId) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        String typeName = NotificationTypeEnum.getNameByType(notification.getType());
        notificationDTO.setTypeName(typeName);
        if (!notificationDTO.getReceiver().equals(userId)) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        //设置状态为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        //找到问题id
        if (notification.getType().equals(NotificationTypeEnum.REPLY_QUESTION.getType())) {
            return notification.getOuterId();
        } else {
            Integer commentId = notification.getOuterId();
            Comment comment = commentMapper.selectByPrimaryKey(commentId);
            return comment.getParentId();
        }
    }
}
