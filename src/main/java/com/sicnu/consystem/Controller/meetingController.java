package com.sicnu.consystem.Controller;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.SunmmaryMapper;
import com.sicnu.consystem.Mapper.UserMapper;
import com.sicnu.consystem.Pojo.Meeting;
import com.sicnu.consystem.Pojo.Sunmmary;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Pojo.reminder;
import com.sicnu.consystem.Service.MeetingService;
import com.sicnu.consystem.Service.Servicelpml.MeetingServiceLmpl;
import com.sicnu.consystem.Service.Servicelpml.reminderServicelmpl;
import com.sicnu.consystem.Util.StatusEnum;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import com.sun.el.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sicnu.consystem.Service.reminderService;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName meetingController
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/31 21:45
 * @Version v1.0
 */
@RestController
public class meetingController {

    @Resource
    UserMapper userMapper;

    @Resource
    MeetingServiceLmpl meetingService;

    @Resource
    reminderServicelmpl reminderService;
    @PostMapping("/addmeeting")
    public BackFrontMessage addMeeting(
            @RequestParam String name,
            @RequestParam String theme,
            @RequestParam Integer hostmanId,
            @RequestParam String  starttime,
            @RequestParam String endtime,
            @RequestParam String remark,
            @RequestParam Integer[] attendsId
     ){
//        System.out.println("name = " + name);
//        System.out.println("theme = " + theme);
//        System.out.println("hostmanId = " + hostmanId);
//        System.out.println("starttime = " + starttime);
//        System.out.println("endtime = " + endtime);
//        System.out.println("remark = " + remark);
//        System.out.println("attendsId = " + attendsId);

//        去重
        List<Integer> collect = new ArrayList<Integer>(Arrays.asList(attendsId)).stream().distinct().collect(Collectors.toList());
        attendsId=collect.toArray(new Integer[collect.size()]);
        for (Integer i : attendsId) {
            System.out.println("i = " + i);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Meeting meeting=new Meeting();
        meeting.setName(name);
        meeting.setRemark(remark);
        Date startTime= null;
        Date endTime=null;
        // TODO: 2022/3/31  处理时间 
        try {
            startTime = sdf.parse(starttime);
            endTime=sdf.parse(endtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        meeting.setStarttime(startTime);
        meeting.setEndtime(endTime);
        meeting.setTheme(theme);
        System.out.println("startTime = " + startTime);
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        System.out.println("startTime = " + simpleDateFormat.format(startTime));
        User userByUid = userMapper.getUserByUid(hostmanId);
        meeting.setUsername(userByUid.getUsername());
        meeting.setUid(userByUid.getUid());
        System.out.println("meeting = " + meeting);
        reminderService.addMeetingRender("会议预约",meeting,attendsId);
        int i = meetingService.addMeeting(meeting,attendsId);
//        if (i!=0){
//            return new BackFrontMessage(StatusEnum.SUCCESS,"新增会议成功");
//        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }


    @GetMapping("/getmymeeting")
    public BackFrontMessage getMyMeeting(){
        User currentUser = UserAuthenticationUtil.getCurrentUser();
        Map<String, Object> myMeeting = meetingService.getMyMeeting(currentUser.getUid());
        System.out.println("myMeeting = " + myMeeting);
        if (myMeeting!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,myMeeting);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @GetMapping("/getAllUsersByMid")
    public BackFrontMessage getAllUsersByMid(
            @RequestParam int mid
    ){
        BackFrontMessage allUsersByMid = meetingService.getAllUsersByMid(mid);
        return allUsersByMid;
    }


    @PostMapping("/addSunmmary")
    public BackFrontMessage addSunmmary(
            @RequestParam int mid,
            @RequestParam String content
    ){
        System.out.println("mid = " + mid);
        System.out.println("content = " + content);
        BackFrontMessage backFrontMessage = meetingService.addSunmmary(mid, content);
        return backFrontMessage;
    }
    @Resource
    SunmmaryMapper sunmmaryMapper;

    @GetMapping("/findSunmmary")
    public BackFrontMessage findSunmmary(
            @RequestParam int mid
    ){
        Sunmmary sunmmary = sunmmaryMapper.getSunmmary(mid);
        if (sunmmary!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,sunmmary);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @GetMapping("/getAllReminder")
    public BackFrontMessage getAllReminder(){
        List<reminder> allReminder = reminderService.getAllReminder();
        if (allReminder!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,allReminder);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }
}

