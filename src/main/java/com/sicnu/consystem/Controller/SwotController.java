package com.sicnu.consystem.Controller;

/**
 * @ClassName SwotController
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/5 21:59
 * @Version v1.0
 */

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.Swot;
import com.sicnu.consystem.Service.Servicelpml.SwotServicelpml;
import com.sicnu.consystem.Util.StatusEnum;
import org.apache.ibatis.type.YearTypeHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SwotController {

    @Resource
    SwotServicelpml swotServicelpml;

    @PostMapping("/addSwot")
    public BackFrontMessage addSwot(
            @RequestParam String name,
            @RequestParam List<String> advantege,
            @RequestParam List<String>disadvantege,
            @RequestParam List<String> opportunity,
            @RequestParam List<String> threaten
    ){

        System.out.println("advantege = " + advantege);
        System.out.println("disadvantege = " + disadvantege);
        System.out.println("opportunity = " + opportunity);
        System.out.println("threaten = " + threaten);
        Swot swot=new Swot();
        swot.setSwotname(name);
        swot.setDisadvantege(disadvantege);
        swot.setAdvantege(advantege);
        swot.setOpportunity(opportunity);
        swot.setThreaten(threaten);
        BackFrontMessage backFrontMessage = swotServicelpml.addSwot(swot);
        if (backFrontMessage!=null){
            return backFrontMessage;
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @GetMapping("/getUsersAllSwot")
    public BackFrontMessage getUsersAllSwot(){
        BackFrontMessage usersAllSwot = swotServicelpml.getUsersAllSwot();
        return usersAllSwot;
    }

    @PostMapping("/useSwot")
    public BackFrontMessage useSwot(
            @RequestParam int sid,
            @RequestParam int mid
    ){
        System.out.println("\"useSwot\" = " + "useSwot");
        System.out.println("sid = " + sid);
        System.out.println("mid = " + mid);
        BackFrontMessage backFrontMessage = swotServicelpml.useSwot(sid, mid);
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @PostMapping("/giveSwotAnswer")
    public BackFrontMessage giveSwotAnswer(
            @RequestParam int mid,
            @RequestParam int sid,
            @RequestParam List<String> advantege,
            @RequestParam List<String>disadvantege,
            @RequestParam List<String> opportunity,
            @RequestParam List<String> threaten
    ){
        System.out.println("mid = " + mid);
        System.out.println("hid = " + sid);
        System.out.println("advantege = " + advantege);
        System.out.println("disadvantege = " + disadvantege);
        System.out.println("opportunity = " + opportunity);
        System.out.println("threaten = " + threaten);
        BackFrontMessage backFrontMessage = swotServicelpml.addSwotAnswer(mid, sid, advantege, disadvantege, opportunity, threaten);
        return backFrontMessage;
    }

    @PostMapping("/getSwotAnswer")
    public BackFrontMessage getSwotAnswer(
            @RequestParam int mid,
            @RequestParam int sid
    ){
        BackFrontMessage swotAnswer = swotServicelpml.getSwotAnswer(mid, sid);
        return swotAnswer;
    }
}

