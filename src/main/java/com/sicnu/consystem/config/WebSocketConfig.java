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
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/ep").addInterceptors()
                .setAllowedOrigins("*").withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic","queue");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        ChannelInterceptor channelInterceptor=new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                System.out.println("before");
                String token = StompHeaderAccessor.wrap(message).getFirstNativeHeader("token");
                if (token!=null){
                    jwtUtil.parseToken(token);
                    Claims claims = jwtUtil.parseToken(token);
                    int id =(int) claims.get("id");
                    User userByUid = userMapper.getUserByUid(id);
                    if (userByUid!=null) {
                        UserAuthenticationUtil.setCurrentUser(userByUid);
                    }
                }
                return message;
            }

//            @Override
//            public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
//                System.out.println("after afterSendCompletion");
//                if (UserAuthenticationUtil.getCurrentUser()!=null){
//                    UserAuthenticationUtil.removeUser();
//                }
//            }
//
//            @Override
//            public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
//                System.out.println("after afterReceiveCompletion");
//            }
//
//            @Override
//            public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
//                System.out.println("postSend");
//            }
//
//            @Override
//            public Message<?> postReceive(Message<?> message, MessageChannel channel) {
//                System.out.println("after postReceive");
//                return message;
//            }
        };
        registration.interceptors(channelInterceptor);
    }
}

