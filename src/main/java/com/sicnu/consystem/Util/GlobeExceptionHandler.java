package com.sicnu.consystem.Util;

import com.sicnu.consystem.Json.BackFrontMessage;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;

/**
 * @ClassName GlobeExceptionHandler
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/20 16:29
 * @Version v1.0
 */

@RestControllerAdvice
public class GlobeExceptionHandler {

    @Resource
    LogUtil logUtil;

    public BackFrontMessage handleException(Exception e){
        e.printStackTrace();
        logUtil.exception(e.getMessage());
        return new BackFrontMessage(500,"服务器出错",null);
    }
}

