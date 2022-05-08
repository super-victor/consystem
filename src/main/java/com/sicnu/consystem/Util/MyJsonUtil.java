package com.sicnu.consystem.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonUtil
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/4 22:48
 * @Version v1.0
 */
@Component
public class MyJsonUtil {

    public List<Map<String,Object>>DelphiAnswerParese(String answer){
        Object parse = JSON.parse(answer);
        JSONArray objects = JSONObject.parseArray(answer);
        List<Map<String,Object>>res=new ArrayList<>();
        int index=0;
        for (Object object : objects) {
            JSONObject jsonObject = JSONObject.parseObject(object.toString());
            String type = jsonObject.getString("type");
            if (type.equals("1")){
                String value = jsonObject.getString("value");
                Map<String,Object>mid=new HashMap<>();
                mid.put("type",1);
                mid.put("value",value);
                res.add(mid);
            }
            if (type.equals("2")){
                List<Integer>m=new ArrayList<>();
                JSONArray list = JSONObject.parseArray(jsonObject.getString("list"));
                for (Object o : list) {
                    int i = Integer.parseInt(o.toString());
                    m.add(i);
                }
                Map<String,Object>mid=new HashMap<>();
                mid.put("type",2);
                mid.put("value",m);
                res.add(mid);
            }
            if (type.equals("3")){
                String text = jsonObject.getString("text");
                Map<String,Object>mid=new HashMap<>();
                mid.put("type",3);
                mid.put("value",text);
                res.add(mid);
            }
        }
        return res;
    }
}

