package com.sicnu.consystem.Service.Servicelpml;

import com.sicnu.consystem.Pojo.Meeting;
import com.sicnu.consystem.Pojo.reminder;

import java.util.Date;
import java.util.List;

/**
 * @InterfaceName reminderlmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/19 22:05
 * @Version v1.0
 */

public interface reminderServicelmpl {
    void addMeetingRender(String title, Meeting meeting,Integer[] attendsId);

    List<reminder>getAllReminder();
}
