package cc.charles.community.interceptor;

import cc.charles.community.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author charlesdong
 * @version 1.0
 * @date 2020/6/9 下午9:12
 * @since 1.8
 */
@Slf4j
@Component
public class StompHandleShakeInterceptor implements HandshakeInterceptor {

    /**
     * 握手前验证用户是否登录
     *
     * @param serverHttpRequest  请求
     * @param serverHttpResponse 响应
     * @param webSocketHandler   ws处理器
     * @param map                映射
     * @return bool
     * @throws Exception 异常
     */
    @Override
    public boolean beforeHandshake(
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse,
            WebSocketHandler webSocketHandler,
            Map<String, Object> map
    ) throws Exception {
        //通过ServerHttpRequest获得HttpServletRequest
        ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
        HttpServletRequest httpRequest = request.getServletRequest();
        User user = (User) httpRequest.getAttribute("user");
        return user != null;
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse,
            WebSocketHandler webSocketHandler,
            Exception e
    ) {
    }
}
