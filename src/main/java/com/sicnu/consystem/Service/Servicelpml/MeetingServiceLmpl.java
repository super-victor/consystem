package com.sicnu.consystem.Service.Servicelpml;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.Meeting;
import com.sicnu.consystem.Pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MeetingServiceLmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/1 10:47
 * @Version v1.0
 */

public interface MeetingServiceLmpl {
//    int addMeeting(Meeting meeting);

    int addMeeting(Meeting meeting,Integer []uids);

    Map<String,Object>getMyMeeting(int uid);

    BackFrontMessage getAllUsersByMid(int mid);

    BackFrontMessage addSunmmary(int mid,String content);
}

