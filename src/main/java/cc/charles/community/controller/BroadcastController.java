package cc.charles.community.controller;

import cc.charles.community.dto.WsRequestMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import cc.charles.community.dto.WsResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName BroadcastController
 * @description
 * @date 2020/6/28 下午8:34
 * @since 1.8
 */
@Controller
@Slf4j
public class BroadcastController {
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * @param requestMessage 请求数据
     * @return 响应数据
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo默认 消息将被发送到与传入消息相同的目的地
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换
     */
    @MessageMapping("/receive")
    @SendTo("/topic/getResponse")
    public WsResponseMessage broadcast(WsRequestMessage requestMessage) {
        log.info("receive message = {}", JSONObject.toJSONString(requestMessage));
        WsResponseMessage responseMessage = new WsResponseMessage();
        responseMessage.setResponseMessage("BroadcastController receive [" + count.incrementAndGet() + "] records, hello " + requestMessage.getName());
        return responseMessage;
    }

    @GetMapping(value="/broadcast/index")
    public String broadcastIndex(HttpServletRequest req){
        return "websocket/broadcast";
    }
}
