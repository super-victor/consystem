package com.sicnu.consystem.Util;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @ClassName Logservice
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/1 20:44
 * @Version v1.0
 */
@Component
public class LogUtil {
    Logger logger=Logger.getLogger("info");

//    String paten=UserAuthenticationUtil.getCurrentUser().getUsername()+"%s%s";

    public void addLog(String info){
        MDC.put("type","ADD");
        logger.info(info);
    }

    public void deleteLog(String info){
        MDC.put("type","DELETE");
        logger.info(info);
    }
    public void updateLog(String info){
        MDC.put("type","UPDATE");
        logger.info(info);
    }


    public void getinfo(String info, String type){
        MDC.put("type",type);
//        System.out.println("日志操作 "+info);
    }

    public void exception(String info){
        MDC.put("type","EXCEPTION");
        logger.fatal(info);
    }

    public void WARN(String message){
        MDC.put("type","WARNING");
        logger.info(message);
    }

    public void ERROR(String message){
        MDC.put("type","ERROR");
        logger.info(message);
    }
}

