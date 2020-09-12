package cc.charles.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器配置
 * @author charlesdong
 * @version 1.0
 * @cLassName WebConfig
 * @description
 * @date 2019/11/3 上午9:47
 * @since 1.8
 */
@Component
public class WebConfig implements WebMvcConfigurer {

    /**
     * 登录拦截器
     */
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 这里主要是为了注册登录拦截器
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //除了github回调以外，其他的请求全部拦截掉
        List<String> pathList = new ArrayList<>();
        pathList.add("/profile*");
        pathList.add("/publish*");
        List<String> excludeList = new ArrayList<>();
        excludeList.add("/logout");
        excludeList.add("/callback");
        registry.addInterceptor(loginInterceptor).addPathPatterns(pathList).excludePathPatterns(excludeList);
    }
}
