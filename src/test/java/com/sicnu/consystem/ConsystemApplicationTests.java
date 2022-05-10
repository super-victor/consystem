package com.sicnu.consystem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.*;
import com.sicnu.consystem.Pojo.*;
import com.sicnu.consystem.Service.LogService;
import com.sicnu.consystem.Service.Servicelpml.DelphiServicelpml;
import com.sicnu.consystem.Service.Servicelpml.SwotServicelpml;
import com.sicnu.consystem.Service.SwotService;
import com.sicnu.consystem.Util.*;
import com.sun.org.apache.bcel.internal.generic.LUSHR;
import io.jsonwebtoken.Claims;
import jdk.nashorn.internal.ir.annotations.Ignore;
import net.minidev.json.JSONUtil;
import netscape.javascript.JSObject;
import netscape.javascript.JSUtil;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.xml.ws.RespectBinding;
import java.sql.Array;
import java.sql.Date;
import java.util.*;

@SpringBootTest
class ConsystemApplicationTests {

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    ServerConfig serverConfig;

    @Resource
    JwtUtil jwtUtil;

    @Resource
    LogMapper logMapper;

    @Resource
    LogService logService;

    @Resource
    LogUtil logUtil;
    @Test
    void contextLoads() {
        User zhangsan = userMapper.getUserByNameAndPwd("zhangsan", "123456");
        System.out.println(zhangsan);
    }

    @Resource
    SwotMapper swotMapper;
    @Resource
    SwotServicelpml swotServicelpml;
    @Test
    void SwotTest(){
        BackFrontMessage usersAllSwot = swotServicelpml.getUsersAllSwot();
        System.out.println("usersAllSwot = " + usersAllSwot);

//        Swot swot=new Swot();
//        swot.setUid(2);
//        List<String>advanteeg=new ArrayList<>();
//        advanteeg.add("fds");
//        advanteeg.add("dfgg");
//        List<String>disadvanteeg=new ArrayList<>();
//        disadvanteeg.add("fdgf");
//        List<String>opportunity=new ArrayList<>();
//        opportunity.add("fdfgfd");
//        List<String>threaten=new ArrayList<>();
//        threaten.add("fgdg");
//        List<Map<String,Object>>res=new ArrayList<>();
//        for (String s : advanteeg) {
//            Map<String,Object>map=new HashMap<>();
//            map.put("type","advanteeg");
//            map.put("content",s);
//            res.add(map);
//        }
//        for (String s : disadvanteeg) {
//            Map<String,Object>map=new HashMap<>();
//            map.put("type","disadvanteeg");
//            map.put("content",s);
//            res.add(map);
//        }
//        for (String s : opportunity) {
//            Map<String,Object>map=new HashMap<>();
//            map.put("type","opportunity");
//            map.put("content",s);
//            res.add(map);
//        }
//        for (String s : threaten) {
//            Map<String,Object>map=new HashMap<>();
//            map.put("type","threaten");
//            map.put("content",s);
//            res.add(map);
//        }
//        System.out.println("res = " + res);
//        swotMapper.addSwotContent(1, res);
//        List<Swot> allSwot = swotMapper.getAllSwot(2);
//        System.out.println("allSwot = " + allSwot);
//        System.out.println("i = " + list);

//        List<String> swotAdvantege = swotMapper.getSwotAdvantege(2, 3);
//        System.out.println("swotAdvantege = " + swotAdvantege);
//        List<String> swotDisAdvantege = swotMapper.getSwotDisAdvantege(2, 3);
//        List<String> swotOpportunity = swotMapper.getSwotOpportunity(2, 3);
//        List<String> swotThreaten = swotMapper.getSwotThreaten(2, 3);
//        System.out.println("swotDisAdvantege = " + swotDisAdvantege);
//        System.out.println("swotOpportunity = " + swotOpportunity);
//        System.out.println("swotThreaten = " + swotThreaten);
//        type.clear();
//        content.clear();
//        swot.setSwotname("测试");
//        int i = swotMapper.addSwot(swot);
//        System.out.println("i = " + i);
//        System.out.println("swot = " + swot);
    }


    @Test
    void dataTest(){
//        User zhangsan = userMapper.getUserByNameAndPwd("zhangsan", "666666");
//        System.out.println(zhangsan);
        String s = jwtUtil.generateToken("zhangsan");
        System.out.println(s);
//        String getuserfromtoken = jwtUtil.getuserfromtoken(s);
//        System.out.println(getuserfromtoken);
    }

    @Test
    void splittest(){
        String m="D:\\Learning Materials\\web开发\\consystem\\src\\main\\resources\\static\\image\\5909a8a5e7bce77a990cdcb6.jpg";
        String[] split = m.split("\\\\");
//        for (String s : split) {
//            System.out.println(s);
//        }
//        System.out.println(split[split.length-1]);
//        System.out.println(m.matches("\\\\*"));
        System.out.println(m.substring(m.lastIndexOf("\\"), m.length()));
//        System.out.println(serverConfig.getLocalUrl());

    }

    @Test
    void  logtest(){
        List<Log> allLogs = logService.getAllLogs();
        allLogs.forEach((Log l)->{
//            Date date = l.getDate();
            System.out.println(l.getDate());
        });
    }

    @Test
    void JwtTest(){
        String user = jwtUtil.generateToken("张三");
        System.out.println(user);
        String getuserfromtoken = jwtUtil.parseToekn(user);
        System.out.println(getuserfromtoken);


        Map<String,Object>cliams=new HashMap<>();
        cliams.put("username","李四");
        cliams.put("id","1");
        String user1 = jwtUtil.generateToken("张三", cliams);

        Claims s = jwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsaXNpIiwiciI6InkiLCJpZCI6MiwiZXhwIjoxNjQ3ODA0MTk2LCJpYXQiOjE2NDc3NDM3MTYsInVzZXJuYW1lIjoibGlzaSJ9.2NNrwHjT7yhE3uDwjdMV87yRs-vKx7TBDB5feVw5GU8");
        System.out.println(user1);
        String m=(String)s.get("username");
        int n=(int) s.get("id");
        System.out.println(m);
        System.out.println(n);

    }

    @Test
    void formatetest(){
        String m = String.format("%s,年后", "只是");
        System.out.println("m = " + m);
    }

    @Resource
    MeetingMapper meetingMapper;
    @Test
    void  meeting(){
//        List<Meeting> inMeeting = meetingMapper.getFutureMeeting(2);
//        System.out.println("inMeeting = " + inMeeting);
        List<User> allUserByMid = meetingMapper.getAllUserByMid(4);

        System.out.println("allUserByMid = " + allUserByMid);
    }

    @Resource
    DelphiMapper delphiMapper;

    @Resource
    MyJsonUtil myJsonUtil;

    @Resource
    FileMapper fileMapper;

    @Resource
    DelphiServicelpml delphiServicelpml;
    @Test
    void delphitest(){

        List<Questionnaire> allDelphiByMid = delphiMapper.getAllDelphiByMid(6);
        List<Object>RES=new ArrayList<>();
        for (Questionnaire questionnaire : allDelphiByMid) {
            List<Object>container=new ArrayList<>();
            List<Object>res=new ArrayList<>();
            List<String> answerByDpis = delphiMapper.getAnswerByDpis(questionnaire.getDpid());
            Map<Object,Object> con=new HashMap<>();
            List<Delphi> delphis = questionnaire.getDelphis();
            List<List<Map<String, Object>>>lists=new ArrayList<>();
            for (String answerByDpi : answerByDpis) {
                List<Map<String, Object>> maps = myJsonUtil.DelphiAnswerParese(answerByDpi);
                lists.add(maps);
            }
            for (int i = 0; i < lists.get(0).size(); i++) {
                List<Object>m=null;
                if (m==null){
                    Map<String, Object> map = lists.get(0).get(i);
                    Integer x=(Integer)map.get("type");
                    m=new ArrayList<>();
                    if (x==1||x==2){
                        m.add(0);
                        m.add(0);
                        m.add(0);
                        m.add(0);
                    }
                }
                for (int j = 0; j < lists.size(); j++) {
                    Map<String, Object> map = lists.get(j).get(i);
                    Integer x=(Integer)map.get("type");
                    if (x==1){
                        String p=(String)map.get("value");
                        Integer flaf=(Integer)m.get(Integer.parseInt(p)-1);
                        m.set(Integer.parseInt(p)-1,flaf+1);
                    }
                    if (x==3){
                        String p=(String)map.get("value");
                        m.add(p);
                    }
                    if (x==2){
                        List<Integer> l=(List<Integer>)map.get("value");
                        for (Integer s : l) {
                            Integer flaf=(Integer)m.get(s-1);
                            m.set(s-1,flaf+1);
                        }
                    }
                }
                res.add(m);
            }
            container.add(questionnaire);
//            questionnaire
            System.out.println("questionnaire.getDelphis() = " + questionnaire.getDelphis());
            for (Object re : res) {
                System.out.println("re = " + re);
            }
            container.add(res);
            RES.add(container);
        }
//        System.out.println("RES = " + RES);
//        BackFrontMessage delphiDataDetails = delphiServicelpml.getDelphiDataDetails(6);
//        System.out.println("delphiDataDetails = " + delphiDataDetails);
    }

//    @Resource
//    MyJsonUtil myJsonUtil;
    @Test
    void jsontest(){
//        String s="[{\"type\":2,\"list\":[3,2]},{\"type\":1,\"value\":1}]";
//        List<Map<String, Object>> maps = myJsonUtil.DelphiAnswerParese(s);
//        System.out.println("maps = " + maps);
//        File myfile = fileMapper.getMyfile("在线会商系统设计与实现.pdf", "D:\\Learning Materials\\web开发\\consystem\\src\\main\\resources\\static\\file\\在线会商系统设计与实现.pdf", "http://localhost:8082/static/file/\\在线会商系统设计与实现.pdf");
//        System.out.println("myfile = " + myfile);

        Swot swotByid = swotMapper.getSwotByid(6);
        System.out.println("swotByid = " + swotByid);

    }


    @Resource
    SunmmaryMapper sunmmaryMapper;
    @Test
    void userUtiletest(){
//        Map<String,String>m1=new HashMap<>();
//        m1.put("mid","1");
//        m1.put("uid","2");
//        String m="mid=1&uid=2";
//        UserAuthenticationUtil.userLisut.add(m);
//        if (UserAuthenticationUtil.userLisut.contains(m)){
//            System.out.println("已经存在");
//        }
//        String s="mid=5&uid=2&t=1649299826996";
//        int i = s.lastIndexOf("&");
//        String substring = s.substring(0,i);
//        System.out.println("substring = " + substring);
//        UserAuthenticationUtil.userlist.
        Sunmmary sunmmary = sunmmaryMapper.getSunmmary(2);
        System.out.println("sunmmary = " + sunmmary);
    }

    @Resource
    GroupMsgContentMapper groupMsgContentMapper;
    @Test
    void Grouptest(){
//        GroupMsgContent groupMsgContent=new GroupMsgContent();
//        groupMsgContent.setMid(5);
//        groupMsgContent.setFromId(2);
//        groupMsgContent.setContent("dfsdfds");
//        groupMsgContent.setFromName("fdgg");
//        groupMsgContent.setFromProfile("fdsf");
//        groupMsgContent.setMessageTypeId(1);
//        groupMsgContentMapper.addGroupMsgContent(groupMsgContent);
        List<GroupMsgContent> allGroupMsgContent = groupMsgContentMapper.getAllGroupMsgContent(5);
//        System.out.println("allGroupMsgContent = " + allGroupMsgContent);
        for (GroupMsgContent groupMsgContent : allGroupMsgContent) {
            System.out.println("groupMsgContent.getContent() = " + groupMsgContent.getContent());
        }
    }

    @Resource
    reminderMapper reminderMapper;

    @Test
    void remindertest(){
        List<reminder> allReminderByUserId = reminderMapper.getAllReminderByUserId(2);
        for (reminder reminder : allReminderByUserId) {
            System.out.println("reminder = " + reminder);
        }
    }
}
