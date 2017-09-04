package com.oa.web.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by [Zy]
 * 2017/8/10 9:11
 */
@Controller
@RequestMapping("/loginController/manager")
public class LoginController {


    @RequestMapping("/page")
    public String page() {
        return "home";
    }

}
