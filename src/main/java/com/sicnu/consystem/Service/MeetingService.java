package com.sicnu.consystem.Service;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.AttendeeMapper;
import com.sicnu.consystem.Mapper.MeetingMapper;
import com.sicnu.consystem.Mapper.SunmmaryMapper;
import com.sicnu.consystem.Pojo.Meeting;
import com.sicnu.consystem.Pojo.Sunmmary;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Service.Servicelpml.MeetingServiceLmpl;
import com.sicnu.consystem.Util.File.nativeFileUtil;
import com.sicnu.consystem.Util.StatusEnum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MeetingService
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/1 10:47
 * @Version v1.0
 */
@Service
public class MeetingService implements MeetingServiceLmpl {

    @Resource
    MeetingMapper meetingMapper;


    @Resource
    AttendeeMapper attendeeMapper;


    @Resource
    nativeFileUtil fileUtil;

    @Resource
    SunmmaryMapper sunmmaryMapper;
    @Override
    public int addMeeting(Meeting meeting,Integer []uids) {
        Meeting addmeeting=null;
        try {
            int res= meetingMapper.addmeeting(meeting);
            if (res!=0){
                int mid = meeting.getMid();
                for (int uid : uids) {
                    attendeeMapper.addMeetingUsers(mid,uid);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public Map<String, Object> getMyMeeting(int uid) {
        Map<String,Object>mymeeting=new HashMap<>();
        try {
            List<Meeting> futureMeeting = meetingMapper.getFutureMeeting(uid);
            List<Meeting> inMeeting = meetingMapper.getInMeeting(uid);
            List<Meeting> historyMeeting = meetingMapper.getHistoryMeeting(uid);
            mymeeting.put("inmeeting",inMeeting);
            mymeeting.put("futuremeeting",futureMeeting);
            mymeeting.put("historymeeting",historyMeeting);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return mymeeting;
        }
    }

    @Override
    public BackFrontMessage getAllUsersByMid(int mid) {
        List<User> allUserByMid = meetingMapper.getAllUserByMid(mid);
        if (allUserByMid!=null){
            for (User user : allUserByMid) {
                user.setAvatarurl(fileUtil.getNativeAvatarurl(user.getAvatarurl()));
            }
            return new BackFrontMessage(StatusEnum.SUCCESS,allUserByMid);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @Override
    public BackFrontMessage addSunmmary(int mid,String content){
        Sunmmary sunmmary=new Sunmmary();
        sunmmary.setContent(content);
        sunmmary.setMid(mid);
        Sunmmary sunmmary1 = sunmmaryMapper.getSunmmary(mid);
        if (sunmmary1!=null){
            int i1 = sunmmaryMapper.updateSunmmary(sunmmary);
            if (i1!=0){
                return new BackFrontMessage(StatusEnum.SUCCESS,"会议纪要修改成功",null);
            }
            return new BackFrontMessage(StatusEnum.FIAL,"会议纪要已上传",null);
        }
        int i = sunmmaryMapper.addSunmmary(sunmmary);
        if (i!=0){
            return new BackFrontMessage(StatusEnum.SUCCESS,"上传会议纪要成功");
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }
}

