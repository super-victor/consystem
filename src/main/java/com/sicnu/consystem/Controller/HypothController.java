package com.sicnu.consystem.Controller;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.Hypoth;
import com.sicnu.consystem.Service.HypotyService;
import com.sicnu.consystem.Service.Servicelpml.HypothServicelmpl;
import com.sicnu.consystem.Util.StatusEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.invoke.util.BytecodeName;

import javax.annotation.Resource;

/**
 * @ClassName HypothController
 * @Description
 * @Author pan
 * @LastChangeDate 2022/5/3 23:01
 * @Version v1.0
 */
@RestController
public class HypothController {

    @Resource
    HypothServicelmpl hypothService;

    @PostMapping("/AddHypoty")
    public BackFrontMessage AddHypoty(
            @RequestParam String hname,
            @RequestParam String hypoth,
            @RequestParam String evidence
    ){
        Hypoth h=new Hypoth();
        h.setHname(hname);
        h.setHypoth(hypoth);
        h.setEvidence(evidence);
        BackFrontMessage backFrontMessage = hypothService.AddHypoth(h);
        return backFrontMessage;
    }

    @GetMapping("/getHypoth")
    public BackFrontMessage getAllHypoth(){
        BackFrontMessage allHypoth = hypothService.getAllHypoth();
        return allHypoth;
    }

    @PostMapping("/updateHypoth")
    public BackFrontMessage updateHypoth(
            @RequestParam int hid,
            @RequestParam String hname,
            @RequestParam String hypoth,
            @RequestParam String evidence
    ){
        Hypoth h=new Hypoth();
        h.setHid(hid);
        h.setHypoth(hypoth);
        h.setEvidence(evidence);
        h.setHname(hname);
        BackFrontMessage backFrontMessage = hypothService.updateHypoth(h);
        return backFrontMessage;
    }

    @PostMapping("/deleteHypoth")
    public BackFrontMessage deleteHypoth(
            @RequestParam int hid
    ){
        BackFrontMessage backFrontMessage = hypothService.deleteHypoth(hid);
        return backFrontMessage;
    }

    @PostMapping("/giveHypothAnswer")
    public BackFrontMessage giveHypothAnswer(
            @RequestParam int hid,
            @RequestParam int uid,
            @RequestParam int answer,
            @RequestParam int mid
    ){
        BackFrontMessage backFrontMessage = hypothService.giveHypothAnswer(hid, uid, answer,mid);
        return backFrontMessage;
    }

    @PostMapping("/HypothDetails")
    public BackFrontMessage HypothDetails(
            @RequestParam int mid
    ){
        BackFrontMessage backFrontMessage = hypothService.HypothDetails(mid);
        return backFrontMessage;
    }

}

