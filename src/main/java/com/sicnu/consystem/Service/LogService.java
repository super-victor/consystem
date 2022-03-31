package com.sicnu.consystem.Service;

import com.sicnu.consystem.Mapper.LogMapper;
import com.sicnu.consystem.Pojo.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LogService
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/20 16:35
 * @Version v1.0
 */

@Service
public class LogService {

    @Resource
    LogMapper logMapper;

    public List<Log>getAllLogs(){
        List<Log> allLogs = logMapper.getAllLogs();
        if (allLogs!=null){
            return allLogs;
        }
        return null;
    }

    public List<Map<String,Object>>getAllTypes(){
        List<String>list=logMapper.getAllTypes();
        List<Map<String,Object>>res=new ArrayList<>();
        list.forEach((String s)->{
            Map<String ,Object>vessel=new HashMap<>();
            vessel.put("text",s);
            vessel.put("value",s);
            res.add(vessel);
        });
        if (res.size()!=0&&res!=null){
            return res;
        }
        return null;
    }

    public boolean deleteLog(int id){
        int i = logMapper.deleteLog(id);
        if (i!=0){
            return true;
        }
        return false;
    }
}

