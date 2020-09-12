package cc.charles.community.advice;

import cc.charles.community.dto.ResultDTO;
import cc.charles.community.exception.CustomizeException;
import cc.charles.community.exception.CustomizeJsonException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author charlesdong
 * @version 1.0
 * @cLassName CustomizeHandler
 * @description
 * @date 2019/12/8 下午10:16
 * @since 1.8
 */
@ControllerAdvice
public class CustomizeHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomizeException.class)
    ModelAndView handleControllerException(Throwable e, Model model) {
        model.addAttribute("message", e.getMessage());
        return new ModelAndView("error");
    }

    @ExceptionHandler(CustomizeJsonException.class)
    @ResponseBody
    ResultDTO handleControllerJsonException(Throwable e) {
        CustomizeJsonException je = (CustomizeJsonException) e;
        return ResultDTO.errorOf(je);
    }
}
