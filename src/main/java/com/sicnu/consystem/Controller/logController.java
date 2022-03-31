package com.sicnu.consystem.Controller;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.Log;
import com.sicnu.consystem.Service.LogService;
import com.sicnu.consystem.Util.StatusEnum;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName logController
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/18 9:17
 * @Version v1.0
 */
// 日志
@RestController
public class logController {


    @Resource
    LogService logService;

    @GetMapping("/getAllLogs")
    public BackFrontMessage getAllLogs(){
        List<Log> allLogs = logService.getAllLogs();
        if (allLogs!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,allLogs);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @GetMapping("/getAllTypes")
    public BackFrontMessage getAllTypes(){
        List<Map<String, Object>> allTypes = logService.getAllTypes();
        if (allTypes!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,allTypes);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @DeleteMapping("/logDelete")
    public BackFrontMessage deleteLog(int lid){
        boolean b = logService.deleteLog(lid);
        if (b){
            return new BackFrontMessage(StatusEnum.SUCCESS,"删除日志成功");
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }
}

