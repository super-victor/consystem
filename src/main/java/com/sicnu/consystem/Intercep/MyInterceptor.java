package com.sicnu.consystem.Intercep;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicnu.consystem.Mapper.UserMapper;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Util.JwtUtil;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyInterceptor implements HandlerInterceptor {


    @Resource
    JwtUtil jwtUtil;

    @Resource
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("requestURL = " + requestURL);
        if (token==null){
            response.setContentType("application/json;charset=utf-8");
            Map<String, Object> res=new HashMap<>();
            res.put("status",500);
            res.put("info","token不存在");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonData = objectMapper.writeValueAsString(res);
            PrintWriter out = response.getWriter();
            out.write(jsonData);
            return false;
        }
        else {
            Claims claims = jwtUtil.parseToken(token);
            int id =(int) claims.get("id");
            User userByUid = userMapper.getUserByUid(id);
            if (userByUid!=null){
                UserAuthenticationUtil.setCurrentUser(userByUid);
            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("清除UserAuthenticationUtil");
        UserAuthenticationUtil.removeUser();
    }
}
