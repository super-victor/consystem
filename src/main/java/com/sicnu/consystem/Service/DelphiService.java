package com.sicnu.consystem.Service;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.DelphiMapper;
import com.sicnu.consystem.Pojo.Delphi;
import com.sicnu.consystem.Pojo.DelphiAnswer;
import com.sicnu.consystem.Pojo.Questionnaire;
import com.sicnu.consystem.Service.Servicelpml.DelphiServicelpml;
import com.sicnu.consystem.Util.MyJsonUtil;
import com.sicnu.consystem.Util.StatusEnum;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DelphiService
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/2 12:38
 * @Version v1.0
 */
@Service
public class DelphiService implements DelphiServicelpml
{

    @Resource
    DelphiMapper delphiMapper;


    @Resource
    MyJsonUtil myJsonUtil;
    @Override
    public BackFrontMessage addDelphi(Delphi delphi) {
        Delphi delphiExsite = delphiMapper.isDelphiExsite(delphi);
        if (delphiExsite!=null){
            return new BackFrontMessage(StatusEnum.FIAL,"德尔菲问题已存在");
        }
        int res=0;
        res = delphiMapper.addDelphi(delphi);
        return new BackFrontMessage(StatusEnum.SUCCESS,"新增德尔菲成功");
    }

    @Override
    public BackFrontMessage getAllDelphis() {
        List<Delphi> allDelphi = delphiMapper.getAllDelphi(UserAuthenticationUtil.getCurrentUser().getUid());
        if (allDelphi!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,allDelphi);
        }return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @Override
    public BackFrontMessage addQuestionnaire(String dpname,Integer[]dids) {
        Questionnaire questionnaire=new Questionnaire();
        questionnaire.setDpname(dpname);
        int questionnaire1 = delphiMapper.addQuestionnaire(questionnaire);
        if (questionnaire1==0){
            return new BackFrontMessage(StatusEnum.FIAL,null);
        }else {
            for (Integer did : dids) {
                delphiMapper.addQuestionnaireContent(did,questionnaire.getDpid());
            }
            return new BackFrontMessage(StatusEnum.SUCCESS,"新增问卷成功");
        }
    }

    @Override
    public BackFrontMessage getAllQuestionNaire() {
        List<Questionnaire> allQuestionNaire = delphiMapper.getAllQuestionNaire(UserAuthenticationUtil.getCurrentUser().getUid());
        if (allQuestionNaire!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,allQuestionNaire);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @Override
    public int addDelphiAnswer(int uid, int mid, int dpid, String answer) {
        int i = delphiMapper.addDelphiAnswer(uid, mid, dpid, answer);
        return i;
    }

    @Override
    public DelphiAnswer findOneDelphiAnswer(int uid, int mid, int dpid) {
        return delphiMapper.findOneDelphiAnswer(uid,mid,dpid);
    }


    @Override
    public BackFrontMessage getDelphiDataDetails(int mid) {
        List<Questionnaire> allDelphiByMid = delphiMapper.getAllDelphiByMid(mid);
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
            container.add(res);
            RES.add(container);
        }
        if (RES!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,RES);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @Override
    public BackFrontMessage modifyDelphi(Delphi delphi) {
        int delphiUsed = delphiMapper.isDelphiUsed(delphi.getDid());
        if (delphiUsed>0){
            return new BackFrontMessage(400,"德尔菲问题已使用",null);
        }
        else {
            int i = delphiMapper.modifyDelphi(delphi);
            if (i!=0){
                return new BackFrontMessage(StatusEnum.SUCCESS,"德尔菲问题修改成功",null);
            }
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @Override
    public BackFrontMessage deleteDelphi(int did) {
        int delphiUsed = delphiMapper.isDelphiUsed(did);
        if (delphiUsed!=0){
            return new BackFrontMessage(400,"德尔菲问题已使用",null);
        }
        int i = delphiMapper.deleteDelphi(did);
        if (i!=0){
            return new BackFrontMessage(StatusEnum.SUCCESS,"删除德尔菲问题成功",null);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }
}

