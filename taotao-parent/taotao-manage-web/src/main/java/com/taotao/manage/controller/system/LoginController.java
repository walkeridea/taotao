package com.taotao.manage.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by walker on 2017/1/18.
 */
@Controller
public class LoginController {

    @RequestMapping("login_toLogin")
    public String toLogin(){

        return "login";
    }

    @RequestMapping("login_login")
    public void login(){


    }



}
