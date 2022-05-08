package com.sicnu.consystem.Mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName AttendeeMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/1 11:37
 * @Version v1.0
 */
@Mapper
public interface AttendeeMapper {
    int addMeetingUsers(int mid,int uid);
}
