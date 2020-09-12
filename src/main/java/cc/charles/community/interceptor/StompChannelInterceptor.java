package cc.charles.community.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author charlesdong
 * @version 1.0
 * @date 2020/7/5 下午1:20
 * @since 1.8
 */
@Component
@Slf4j
public class StompChannelInterceptor implements ChannelInterceptor {

    @Override
    public boolean preReceive(MessageChannel channel) {
        log.info("StompChannelInterceptor preReceive");
        return true;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        log.info("StompChannelInterceptor preSend");
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
        StompCommand command = stompHeaderAccessor.getCommand();
        if (StompCommand.SUBSCRIBE.equals(command)) {
            log.info("用户订阅目的地=" + stompHeaderAccessor.getDestination());
            // 如果该用户订阅的频道不合法直接返回null前端用户就接受不到该频道信息
            return message;
        } else {
            return message;
        }
    }


    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, @Nullable Exception ex) {
        log.info("StompChannelInterceptor afterSendCompletion");
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(message);
        StompCommand command = stompHeaderAccessor.getCommand();
        if (StompCommand.SUBSCRIBE.equals(command)) {
            log.info("订阅消息发送成功");
        }
        if (StompCommand.DISCONNECT.equals(command)) {
            log.info("用户断开连接成功");
        }
    }
}
