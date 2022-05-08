package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.reminder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName reminderMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/19 22:41
 * @Version v1.0
 */
@Mapper
public interface reminderMapper {
    int addMeetingReminder(reminder reminder);
    List<reminder>getAllReminderByUserId(int uid);
}

