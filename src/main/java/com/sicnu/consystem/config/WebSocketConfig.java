package com.sicnu.consystem.config;

import com.sicnu.consystem.Mapper.UserMapper;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Util.JwtUtil;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.net.URI;
import java.util.*;

/**
 * @ClassName WebSocketConfig
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/29 21:36
 * @Version v1.0
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Resource
    JwtUtil jwtUtil;

    @Resource
    UserMapper userMapper;


//    List<Map<String,String>>userlist=new ArrayList<>();

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/ep").addInterceptors(new HttpHandShakeInterceptor())
                .setAllowedOrigins("*").withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "queue");

    }


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        ChannelInterceptor channelInterceptor=new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                System.out.println("发消息了");
//                String token = StompHeaderAccessor.wrap(message).getFirstNativeHeader("token");
//                if (token!=null){
//                    jwtUtil.parseToken(token);
//                    Claims claims = jwtUtil.parseToken(token);
//                    int id =(int) claims.get("id");
//                    User userByUid = userMapper.getUserByUid(id);
//                    if (userByUid!=null) {
//                        UserAuthenticationUtil.setCurrentUser(userByUid);
//                    }
//                }
                return message;
            }

//            @Override
//            public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
//                System.out.println("连接了");
//                if (UserAuthenticationUtil.getCurrentUser()!=null){
//                    UserAuthenticationUtil.removeUser();
//                }
//            }
//
//            @Override
//            public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
//                System.out.println("连接了");
//            }
//
//            @Override
//            public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
//                System.out.println("连接了");
//            }
//
//            @Override
//            public Message<?> postReceive(Message<?> message, MessageChannel channel) {
//                System.out.println("连接了");
//                return message;
//            }
        };
        registration.interceptors(channelInterceptor);
    }
}

class HttpHandShakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("连接了 websocke");

//        ServletServerHttpRequest servletServerHttpRequest=(ServletServerHttpRequest)serverHttpRequest;
//
//        String query = serverHttpRequest.getURI().getQuery();
//        System.out.println("query = " + query);
//        String substring = query.substring(0, query.lastIndexOf("&"));
//        System.out.println("substring = " + substring);
//        if (UserAuthenticationUtil.userLisut.contains(query)||UserAuthenticationUtil.userLisut.contains(substring)){
//            System.out.println("已连接,拒绝再次拦截");
//            return false;
//        }
//        UserAuthenticationUtil.userLisut.add(query);
//        Map<String, String> stringStringMap = parseParameterMap(query);
//        if ()
//        String mid = stringStringMap.get("mid");
//        String uid = stringStringMap.get("uid");
//        System.out.println("mid = " + mid);
//        System.out.println("uid = " + uid);
//        System.out.println("query = " + query);
//        HttpHeaders headers = servletServerHttpRequest.getHeaders();
//        for (String s : headers.keySet()) {
//            List<String> list = headers.get(s);
//            System.out.println(s+":" + list);
//        }
//        List<String> list = headers.get("cookie");
//        for (String s : list) {
//            System.out.println("s = " + s);
//        }
//        ServletServerHttpRequest httpRequest = (ServletServerHttpRequest) serverHttpRequest;
//
//        Enumeration<String> message = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest().getHeaderNames();
//        while (message.hasMoreElements()){
//            System.out.println("message.nextElement() = " + message.nextElement());
//        }
//        serverHttpRequest.
//        webSocketHandler.

//        boolean b = handleBeCookie(serverHttpRequest);
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }

    public boolean handleBeCookie(ServerHttpRequest serverHttpRequest) {

        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) serverHttpRequest;

//            Cookie[] cookies = servletServerHttpRequest.getServletRequest().getCookies();
//            for (Cookie cookie : cookies) {
//                System.out.println("cookie.getName() = " + cookie.getName());
//            }
//            Optional<Cookie> mid = Arrays.stream(cookies).filter(c -> c.getName().equals("mid")).findFirst();
//            if (mid.isPresent()){
//                String value = mid.get().getValue();
//                System.out.println("mid = " + value);
//            }
        }
        return false;
    }

    public Map<String, String> parseParameterMap(String queryString) {
        Map<String, String> parameterMap = new HashMap<>();
        String[] parameters = queryString.split("&");
        for (String parameter : parameters) {
            String[] paramPair = parameter.split("=");
            if (paramPair.length == 2) {
                parameterMap.put(paramPair[0], paramPair[1]);
            }
        }
        return parameterMap;
    }
}

