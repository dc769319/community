package cc.charles.community.config;

import cc.charles.community.model.User;
import cc.charles.community.provider.GithubProvider;
import cc.charles.community.service.NotificationService;
import cc.charles.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName HttpServletRequestWrapperFilter
 * @description
 * @date 2020/6/14 下午5:05
 * @since 1.8
 */
@WebFilter(filterName = "httpServletRequestWrapperFilter", urlPatterns = "/*")
public class HttpServletRequestWrapperFilter implements Filter {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //获取github授权登录地址
        String callbackUrl = githubProvider.getCallbackUrl();
        request.setAttribute("callbackUrl", callbackUrl);
        //查询用户cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            User user = null;
            Long unRead = null;
            //cookie不为空则遍历获取cookie
            for (Cookie cur : cookies) {
                if (cur.getName().equals("token")) {
                    //根据token查询用户身份信息
                    String token = cur.getValue();
                    user = userService.getUserByToken(token);
                    //查询用户未读通知数
                    unRead = notificationService.unReadCount(user.getId());
                    break;
                }
            }
            if (user != null) {
                //将用户信息保存到request，供controller读取用户信息
                request.setAttribute("user", user);
                request.setAttribute("unRead", unRead);
                LoginUserPrincipal principal = new LoginUserPrincipal(user.getName());
                filterChain.doFilter(new CustomServletRequestWrapper((HttpServletRequest) servletRequest, principal), servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
