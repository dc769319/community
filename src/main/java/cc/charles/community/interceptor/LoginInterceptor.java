package cc.charles.community.interceptor;

import cc.charles.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器，验证用户是否已经登录，未登录则自动跳转到github授权登录
 * @author charlesdong
 * @version 1.0
 * @cLassName LoginInterceptor
 * @description
 * @date 2019/11/3 上午9:49
 * @since 1.8
 */
@Service
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private GithubProvider githubProvider;

    /**
     * 在业务处理器处理请求之前被调用
     * @param request 请求
     * @param response 回复
     * @param handler 处理器
     * @return bool
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String callbackUrl = githubProvider.getCallbackUrl();
        //查询用户cookie
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            //cookie为空，调用github登录
            response.sendRedirect(callbackUrl);
            return false;
        }
        String token = null;
        for (Cookie cur : cookies) {
            if (cur.getName().equals("token")) {
                //根据token查询用户身份信息
                token = cur.getValue();
                break;
            }
        }
        //cookie中不存在token也调用github登录
        if(token == null) {
            response.sendRedirect(callbackUrl);
            return false;
        }
        return true;
    }
}
