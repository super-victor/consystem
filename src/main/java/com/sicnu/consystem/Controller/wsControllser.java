package com.sicnu.consystem.Controller;

import com.github.binarywang.java.emoji.EmojiConverter;
import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.GroupMsgContentMapper;
import com.sicnu.consystem.Mapper.UserMapper;
import com.sicnu.consystem.Pojo.GroupMsgContent;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Util.File.FileUtil;
import com.sicnu.consystem.Util.File.nativeFileUtil;
import com.sicnu.consystem.Util.JwtUtil;
import com.sicnu.consystem.Util.StatusEnum;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName wsControllser
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/29 21:24
 * @Version v1.0
 */
@Controller
public class wsControllser {


    @Resource
    JwtUtil jwtUtil;

    @Resource
    UserMapper userMapper;

    @Resource
    SimpMessagingTemplate simpMessagingTemplate;

    EmojiConverter emojiConverter = EmojiConverter.getInstance();

    @Resource
    nativeFileUtil fileUtil;

    @Resource
    GroupMsgContentMapper groupMsgContentMapper;



    @MessageMapping("/ws/groupChat/{id}")
    public void handleMessage(@DestinationVariable int id, GroupMsgContent groupMsgContent,
                              Message message){
        //获取发送消息的用户id
        String token = StompHeaderAccessor.wrap(message).getFirstNativeHeader("token");
        jwtUtil.parseToken(token);
        Claims claims = jwtUtil.parseToken(token);
        int uid = (int) claims.get("id");
        //获取用户ID
        User userByUid = userMapper.getUserByUid(uid);
        groupMsgContent.setContent(emojiConverter.toHtml(groupMsgContent.getContent()));
        groupMsgContent.setMid(id);
        groupMsgContent.setFromId(uid);
        groupMsgContent.setFromName(userByUid.getUsername());
        groupMsgContent.setFromProfile(fileUtil.getNativeAvatarurl(userByUid.getAvatarurl()));
        groupMsgContentMapper.addGroupMsgContent(groupMsgContent);
        //存储对话
        groupMsgContent.setCreateTime(new Date());
        simpMessagingTemplate.convertAndSend("/topic/greetings",groupMsgContent);
        System.out.println("groupMsgContent = " + groupMsgContent);
        //消息转发
    }


//    @GetMapping("/chat/{id}")
//    public BackFrontMessage handleGroupMessage(@PathVariable int id){
//        return new BackFrontMessage(StatusEnum.SUCCESS,id);
//    }





}

