package com.sicnu.consystem.Intercep;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicnu.consystem.Util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
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
        System.out.println("解析中");
        //System.out.println(new JwtUtil().getuserfromtoken(token)+"解析");
        String username=new JwtUtil().getuserfromtoken(token);
        System.out.println(username);
//        if (!username.equals("admin")){
//            response.setContentType("application/json;charset=utf-8");
//            Map<String, Object> res=new HashMap<>();
//            res.put("status",500);
//            res.put("info","用户不存在");
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonData = objectMapper.writeValueAsString(res);
//            PrintWriter out = response.getWriter();
//            out.write(jsonData);
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
