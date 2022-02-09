package com.sicnu.consystem.Intercep;


import com.sicnu.consystem.Util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName loginInterceptor
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/30 23:10
 * @Version v1.0
 */
@Component
public class loginInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JwtUtil jwtUtil=new JwtUtil();
        String username=request.getParameter("username");
        String token= jwtUtil.generateToken(username);
        System.out.println(token);
        response.addHeader("token",token);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        response.addHeader("token","dsfsdfs");
    }
}

