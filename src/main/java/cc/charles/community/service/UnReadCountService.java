package cc.charles.community.service;

import cc.charles.community.dto.UnreadMsgDTO;
import cc.charles.community.dto.WsResponseMessage;
import cc.charles.community.mapper.NotificationExtMapper;
import cc.charles.community.mapper.UserExtMapper;
import cc.charles.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author charlesdong
 * @version 1.0
 * @date 2020/7/11 下午3:30
 * @since 1.8
 */
@Service
public class UnReadCountService {

    private static final String UN_READ_COUNT_CHANNEL = "/queue/unread";

    private UserExtMapper userExtMapper;
    private NotificationExtMapper notificationExtMapper;
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public UnReadCountService(
            UserExtMapper userExtMapper,
            NotificationExtMapper notificationExtMapper,
            SimpMessagingTemplate messagingTemplate
    ) {
        this.userExtMapper = userExtMapper;
        this.notificationExtMapper = notificationExtMapper;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 刷新未读消息数
     *
     * @param userId 用户id
     */
    public void refresh(Integer userId) {
        //根据userId获取User
        User user = userExtMapper.getById(userId);
        //统计未读数
        Integer unReadCount = notificationExtMapper.refreshUnReadCount(userId);
        //发消息给用户
        UnreadMsgDTO unreadMsgDTO = new UnreadMsgDTO();
        unreadMsgDTO.setNum(unReadCount);
        WsResponseMessage<UnreadMsgDTO> responseMessage = new WsResponseMessage<>();
        responseMessage.setResponseMessage("Success");
        responseMessage.setData(unreadMsgDTO);
        messagingTemplate.convertAndSendToUser(user.getName(), UN_READ_COUNT_CHANNEL, responseMessage);
    }
}
