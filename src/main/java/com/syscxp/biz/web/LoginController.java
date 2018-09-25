package com.syscxp.biz.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录页面
 *
 * @author HenryYan
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String login() {

//        ModelAndView mav = new ModelAndView("/login.jsp");

        return "login";
    }

}
