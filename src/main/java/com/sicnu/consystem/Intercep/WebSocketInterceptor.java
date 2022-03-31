//package com.sicnu.consystem.Intercep;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sicnu.consystem.Mapper.UserMapper;
//import com.sicnu.consystem.Pojo.User;
//import com.sicnu.consystem.Util.JwtUtil;
//import com.sicnu.consystem.Util.UserAuthenticationUtil;
//import io.jsonwebtoken.Claims;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//import org.springframework.web.socket.server.HandshakeHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @ClassName WebSocketInterceptor
// * @Description
// * @Author pan
// * @LastChangeDate 2022/3/30 22:29
// * @Version v1.0
// */
//@Component
//public class WebSocketInterceptor implements HandshakeInterceptor {
//
//
//    @Resource
//    JwtUtil jwtUtil;
//
//    @Resource
//    UserMapper userMapper;
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
//        HttpServletRequest request=((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
//        String token=request.getHeader("token");
////        if (token==null){
////            response.setContentType("application/json;charset=utf-8");
////            Map<String, Object> res=new HashMap<>();
////            res.put("status",500);
////            res.put("info","token不存在");
////            ObjectMapper objectMapper = new ObjectMapper();
////            String jsonData = objectMapper.writeValueAsString(res);
////            PrintWriter out = response.getWriter();
////            out.write(jsonData);
////            return false;
////        }
//        if(token!=null) {
//            System.out.println("websocket:"+token);
//            Claims claims = jwtUtil.parseToken(token);
//            int id =(int) claims.get("id");
//            User userByUid = userMapper.getUserByUid(id);
//            if (userByUid!=null){
//                UserAuthenticationUtil.setCurrentUser(userByUid);
//            }else {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
//        UserAuthenticationUtil.removeUser();
//    }
//}
//
