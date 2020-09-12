package cc.charles.community.controller;

import cc.charles.community.dto.PaginationDTO;
import cc.charles.community.dto.QuestionDTO;
import cc.charles.community.model.User;
import cc.charles.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName HelloController
 * @description
 * @date 2019/8/18 下午2:48
 * @since 1.8
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = {"", "/"})
    public String index(
            Model model,
            @RequestParam(name = "page", defaultValue = "1") Long page,
            @RequestParam(name = "pageSize", defaultValue = "10") Long pageSize,
            @RequestParam(name = "keyword", required = false) String keyword
    ) {
        //获取数据列表
        questionService.initList(page, pageSize, keyword);
        PaginationDTO paginationDTO = questionService.getPaginationDTO();
        List<QuestionDTO> questionDTOList = questionService.getQuestionDTOList();
        model.addAttribute("paginationDTO", paginationDTO);
        model.addAttribute("questionDTOList", questionDTOList);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "index";
    }
}
