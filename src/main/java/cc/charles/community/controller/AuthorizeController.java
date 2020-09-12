package cc.charles.community.controller;

import cc.charles.community.dto.AccessTokenDTO;
import cc.charles.community.dto.GithubUser;
import cc.charles.community.exception.CustomizeErrorCode;
import cc.charles.community.exception.CustomizeException;
import cc.charles.community.provider.GithubProvider;
import cc.charles.community.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName AuthorizeController
 * @description
 * @date 2019/8/18 下午10:27
 * @since 1.8
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    /**
     * github OAuth回调地址
     *
     * @param code
     * @param state
     * @return
     */
    @GetMapping(value = "/callback")
    public String callback(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "state") String state,
            Model model,
            HttpServletResponse response
    ) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(githubProvider.getClientId());
        accessTokenDTO.setClient_secret(githubProvider.getClientSecret());
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(githubProvider.getClientRedirectUri());
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("accessToken:" + accessToken);
        //获取到github的用户信息
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            //验证name属性是否为空
            String githubName = githubUser.getName();
            if (githubName == null) {
                //name为空提示错误信息
                throw new CustomizeException(CustomizeErrorCode.LOGIN_EXCEPTION);
            }
            String userToken = userService.saveUser(githubUser);
            //设置一个cookie，保存token
            Cookie cookie = new Cookie("token", userToken);
            cookie.setMaxAge(3600);
            response.addCookie(new Cookie("token", userToken));
            //将用户信息写入到session中（没有使用db保存token时，可以使用）
            //request.getSession().setAttribute("user", githubUser);
            logger.info("name:" + githubUser.getName());
            logger.info("bio:" + githubUser.getBio());
            logger.info("email:" + githubUser.getEmail());
        }
        //重定向到首页
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
