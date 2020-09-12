package cc.charles.community.controller;

import cc.charles.community.exception.CustomizeErrorCode;
import cc.charles.community.exception.CustomizeException;
import cc.charles.community.model.User;
import cc.charles.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName NotificationController
 * @description
 * @date 2020/5/1 下午5:07
 * @since 1.8
 */
@Controller
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String info(
            @PathVariable("id") Integer id,
            HttpServletRequest request
            ) {
        User user = (User) request.getAttribute("user");
        if(null == user) {
            throw new CustomizeException(CustomizeErrorCode.LOGIN_EXCEPTION);
        }
        Integer quesId = notificationService.read(id, user.getId());
        //重定向到首页
        return "redirect:/question/" + quesId;
    }
}
