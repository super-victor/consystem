package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.Meeting;
import com.sicnu.consystem.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MeetingMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/1 10:41
 * @Version v1.0
 */
@Mapper
public interface MeetingMapper {
    int addmeeting(Meeting meeting);

    List<Meeting> getInMeeting(int uid);

    List<Meeting>getFutureMeeting(int uid);

    List<Meeting>getHistoryMeeting(int uid);

    List<User>getAllUserByMid(int mid);

    List<User> getAllUserByMid();
}

