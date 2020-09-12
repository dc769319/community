package cc.charles.community.controller;

import cc.charles.community.dto.WsRequestMessage;
import cc.charles.community.dto.WsResponseMessage;
import cc.charles.community.exception.CustomizeException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author charlesdong
 * @version 1.0
 * @date 2020/7/5 下午2:14
 * @since 1.8
 */
@Controller
@Slf4j
public class Point2PointController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private static final String P2P_MSG_PATH = "/queue/getResponse";

    private AtomicInteger count = new AtomicInteger(0);

    @MessageMapping("/receive-single")
    public void send(WsRequestMessage requestMessage, StompHeaderAccessor stompHeaderAccessor) {
        log.info("receive message = {}", JSONObject.toJSONString(requestMessage));
        WsResponseMessage responseMessage = new WsResponseMessage();
        responseMessage.setResponseMessage("Point2PointController receive [" + count.incrementAndGet() + "] records, hello " + requestMessage.getName());
        Principal principal = stompHeaderAccessor.getUser();
        if (ObjectUtils.isEmpty(principal)) {
            throw new CustomizeException("User not login");
        }
        log.info("userPrincipal: {}", principal.getName());
        //注意：点对点server->client的消息地址，不包括/user前缀，并且该消息地址的前缀必须在configureMessageBroker方法中传入，这里就是指/queue
        simpMessagingTemplate.convertAndSendToUser(principal.getName(), P2P_MSG_PATH, responseMessage);
    }

    @GetMapping("/point2point/index")
    public String index() {
        return "websocket/point2point";
    }
}
