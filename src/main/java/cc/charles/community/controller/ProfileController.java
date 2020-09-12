package cc.charles.community.controller;

import cc.charles.community.dto.NotificationDTO;
import cc.charles.community.dto.PaginationDTO;
import cc.charles.community.dto.QuestionDTO;
import cc.charles.community.exception.CustomizeErrorCode;
import cc.charles.community.exception.CustomizeException;
import cc.charles.community.mapper.UserMapper;
import cc.charles.community.model.User;
import cc.charles.community.service.NotificationService;
import cc.charles.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName ProfileController
 * @description
 * @date 2019/10/27 下午8:47
 * @since 1.8
 */
@Controller
@Slf4j
public class ProfileController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action,
            Model model,
            HttpServletRequest request,
            @RequestParam(name = "page", defaultValue = "1") Long page,
            @RequestParam(name = "pageSize", defaultValue = "2") Long pageSize
    ) {
        User user = (User) request.getAttribute("user");

        if(null == user) {
            throw new CustomizeException(CustomizeErrorCode.LOGIN_EXCEPTION);
        }
        log.info("Principal: " + request.getUserPrincipal().getName());
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
            //获取数据列表
            questionService.initList(page, pageSize, user.getId());
            PaginationDTO paginationDTO = questionService.getPaginationDTO();
            List<QuestionDTO> questionDTOList = questionService.getQuestionDTOList();
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("questionDTOList", questionDTOList);
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            notificationService.initList(page, pageSize, user.getId());
            PaginationDTO paginationDTO = notificationService.getPaginationDTO();
            List<NotificationDTO> notificationDTOList = notificationService.getNotificationDTOList();
            model.addAttribute("paginationDTO", paginationDTO);
            model.addAttribute("notificationDTOList", notificationDTOList);
        }
        model.addAttribute("page", page);
        return "profile";
    }
}
