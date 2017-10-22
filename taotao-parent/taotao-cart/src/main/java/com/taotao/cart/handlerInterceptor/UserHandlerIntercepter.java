package com.taotao.cart.handlerInterceptor;


import com.taotao.cart.bean.User;
import com.taotao.cart.service.PropertieService;
import com.taotao.cart.service.UserService;
import com.taotao.cart.threadlocal.UserThreadLocal;
import com.taotao.common.utils.CookieUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserHandlerIntercepter implements HandlerInterceptor{

    public static final String COOKIE_NAME = "TT_TOKEN";

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        UserThreadLocal.set(null);//清空当前线程中的User对象
        String token= CookieUtils.getCookieValue(request,COOKIE_NAME);
        if(StringUtils.isEmpty(token)){
            //未登陆状态
            return true;
        }

        User user = this.userService.queryUserByToken(token);
        if(null==user){
            //未登陆状态
            return true;
        }
        //处于登陆状态
        UserThreadLocal.set(user);//将user对象放入
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
