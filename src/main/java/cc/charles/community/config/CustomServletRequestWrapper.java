package cc.charles.community.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName CustomServletRequestWrapper
 * @description
 * @date 2020/6/14 下午4:53
 * @since 1.8
 */
public class CustomServletRequestWrapper extends HttpServletRequestWrapper {

    private final LoginUserPrincipal loginUserPrincipal;

    CustomServletRequestWrapper(HttpServletRequest request, LoginUserPrincipal principal) {
        super(request);
        this.loginUserPrincipal = principal;
    }

    /**
     * 重写该方法，让HttpServletRequest获取我们设置的principal
     * @return principal
     */
    @Override
    public Principal getUserPrincipal() {
        return this.loginUserPrincipal;
    }
}
