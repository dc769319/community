package cc.charles.community.config;

import java.security.Principal;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName LoginUserPrincipal
 * @description
 * @date 2020/6/9 下午8:53
 * @since 1.8
 */
public class LoginUserPrincipal implements Principal {

    private String name;

    public LoginUserPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
