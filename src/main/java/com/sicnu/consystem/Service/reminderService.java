package com.sicnu.consystem.Service;
import com.sicnu.consystem.Mapper.reminderMapper;
import com.sicnu.consystem.Pojo.Meeting;
import com.sicnu.consystem.Pojo.reminder;
import com.sicnu.consystem.Service.Servicelpml.reminderServicelmpl;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName reminderService
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/19 22:07
 * @Version v1.0
 */
@Service
public class reminderService implements reminderServicelmpl{

    @Resource
    reminderMapper reminderMapper;

    @Override
    public void addMeetingRender(String title, Meeting meeting,Integer[] attendsId) {
        String x="用户张三在2022-03-23 19:40预约了会议，会议开始时间为2022-03-24 10:40，结束时间2022-03-24 11:40." +
                " 主题是：如何解决工作效率问题。 ";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String StratTime=simpleDateFormat.format(meeting.getStarttime());
        String EndTime=simpleDateFormat.format(meeting.getEndtime());
        String AddTime=simpleDateFormat.format(new Date());
        String content= String.format("用户%s在%s预约了会议，" +
                "会议开始时间为%s，结束时间%s. " +
                "主题是：%s.主要内容：%s", UserAuthenticationUtil.getCurrentUser().getUsername(),AddTime,StratTime,EndTime,meeting.getTheme(),
                meeting.getRemark());
        reminder reminder=new reminder();
        reminder.setContent(content);
        reminder.setTitle(title);
        for (Integer integer : attendsId) {
            reminder.setUid(integer);
            reminderMapper.addMeetingReminder(reminder);
        }
        System.out.println("reminder = " + reminder);
    }

    @Override
    public List<reminder> getAllReminder() {
        int uid = UserAuthenticationUtil.getCurrentUser().getUid();
        List<reminder> allReminderByUserId = reminderMapper.getAllReminderByUserId(uid);
        return allReminderByUserId;
    }
}

