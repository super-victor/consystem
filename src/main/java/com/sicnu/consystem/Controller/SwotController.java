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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

