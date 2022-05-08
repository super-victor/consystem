package com.sicnu.consystem.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.Delphi;
import com.sicnu.consystem.Pojo.DelphiAnswer;
import com.sicnu.consystem.Service.DelphiService;
import com.sicnu.consystem.Service.Servicelpml.DelphiServicelpml;
import com.sicnu.consystem.Util.MyJsonUtil;
import com.sicnu.consystem.Util.StatusEnum;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.swing.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @ClassName DelphiController
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/2 12:39
 * @Version v1.0
 */
@RestController
public class DelphiController {



    @Resource
    MyJsonUtil myJsonUtil;

    @Resource
    DelphiServicelpml delphiService;

    @PostMapping("/adddelphi")
    public BackFrontMessage addDelphi(
            @RequestParam String dname,
            @RequestParam int type,
            @RequestParam String delphia,
            @RequestParam String delphib,
            @RequestParam String delphic,
            @RequestParam String delphid
    ){
        Delphi delphi=new Delphi();
        delphi.setType(type);
        delphi.setDname(dname);
        delphi.setUid(UserAuthenticationUtil.getCurrentUser().getUid());
        delphi.setDelphia(delphia);
        delphi.setDelphib(delphib);
        delphi.setDelphic(delphic);
        delphi.setDelphid(delphid);

        System.out.println("delphi = " + delphi);
        BackFrontMessage backFrontMessage = delphiService.addDelphi(delphi);
        if(backFrontMessage!=null){
            return backFrontMessage;
        }
        return new BackFrontMessage(StatusEnum.FIAL,"添加德尔菲失败");
    }


    @PostMapping("/delphimodify")
    public BackFrontMessage modifyDelphi(
            @RequestParam String dname,
            @RequestParam int type,
            @RequestParam String delphia,
            @RequestParam String delphib,
            @RequestParam String delphic,
            @RequestParam String delphid,
            @RequestParam int did
    ){

        System.out.println("delphia = " + delphia);
        System.out.println("delphib = " + delphib);
        System.out.println("delphic = " + delphic);
        System.out.println("delphid = " + delphid);
        Delphi delphi=new Delphi();
        delphi.setDid(did);
        delphi.setDelphia(delphia);
        delphi.setDelphib(delphib);
        delphi.setDelphic(delphic);
        delphi.setDname(dname);
        delphi.setType(type);
        delphi.setDelphid(delphid);
        BackFrontMessage backFrontMessage = delphiService.modifyDelphi(delphi);
        return backFrontMessage;
    }


    @PostMapping("/deleteDelphi")
    public BackFrontMessage deleteDelphi(
            @RequestParam int did
    ){
        BackFrontMessage backFrontMessage = delphiService.deleteDelphi(did);
        return backFrontMessage;
    }

    @GetMapping("/getAlldelphis")
    public BackFrontMessage getAllDelphis(){
        BackFrontMessage allDelphis = delphiService.getAllDelphis();
        return allDelphis;
    }

    @PostMapping("/addquestionnaire")
    public BackFrontMessage addQuestionnaire(
            @RequestParam String dpname,
            @RequestParam Integer[] dids
    ){
        BackFrontMessage backFrontMessage = delphiService.addQuestionnaire(dpname, dids);
        return backFrontMessage;
    }

    @GetMapping("/getAllquestionnaire")
    public BackFrontMessage getAllQuestionNaire(){
        BackFrontMessage allQuestionNaire = delphiService.getAllQuestionNaire();
        return allQuestionNaire;
    }

    @PostMapping("/addQuestionnaireAnswer")
    public BackFrontMessage addQuestionnaireAnswer(
            @RequestParam String answer,
            @RequestParam int mid,
            @RequestParam int dpid
    ){
        System.out.println("answer = " + answer);
        List<Map<String, Object>> maps = myJsonUtil.DelphiAnswerParese(answer);
        System.out.println("maps = " + maps);
        System.out.println("mid = " + mid);
        System.out.println("uid = " + UserAuthenticationUtil.getCurrentUser().getUid());
        int i = delphiService.addDelphiAnswer(UserAuthenticationUtil.getCurrentUser().getUid(), mid, dpid, answer);
        if (i!=0){
            return new BackFrontMessage(StatusEnum.SUCCESS,null);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @GetMapping("/findOneDelphiAnswer")
    public BackFrontMessage findOneDelphiAnswer(
            @RequestParam int mid,
            @RequestParam int dpid
    ){

        DelphiAnswer oneDelphiAnswer = delphiService.findOneDelphiAnswer(UserAuthenticationUtil.getCurrentUser().getUid(), mid, dpid);

        System.out.println("找到oneDelphiAnswer = " + oneDelphiAnswer);
        System.out.println("mid = " + mid);
        System.out.println("dpid = " + dpid);
        if (oneDelphiAnswer!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,null);
        }else {
            return new BackFrontMessage(StatusEnum.FIAL,null);
        }
    }

    @GetMapping("/DelphiDataDetails")
    public BackFrontMessage getDelphiDataDetails(@RequestParam int mid){
        BackFrontMessage delphiDataDetails = delphiService.getDelphiDataDetails(mid);
        return delphiDataDetails;
    }
}

