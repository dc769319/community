package cc.charles.community.config;

import cc.charles.community.interceptor.StompChannelInterceptor;
import cc.charles.community.interceptor.StompHandleShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author charlesdong
 * @version 1.0
 * @date 2020/6/9 下午7:37
 * @since 1.8
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private StompHandleShakeInterceptor stompHandleShakeInterceptor;

    @Autowired
    private StompChannelInterceptor stompChannelInterceptor;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-simple")
                .setAllowedOrigins("*")
                .addInterceptors(stompHandleShakeInterceptor)
                .withSockJS()
                .setHeartbeatTime(20_000);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //启用一个简单的消息代理，并配置一个或多个路由前缀以过滤往发往该代理的消息。
        registry.enableSimpleBroker("/topic", "/queue");
        //给指定用户发送（一对一）的主题前缀是“/user/”
        registry.setUserDestinationPrefix("/user");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(stompChannelInterceptor);
        registration.taskExecutor(taskAsyncPool());
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.taskExecutor(taskAsyncPool());
    }

    private ThreadPoolTaskExecutor taskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
