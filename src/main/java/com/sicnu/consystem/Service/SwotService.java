package com.sicnu.consystem.Service;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.SwotMapper;
import com.sicnu.consystem.Pojo.Swot;
import com.sicnu.consystem.Pojo.User;
import com.sicnu.consystem.Service.Servicelpml.SwotServicelpml;
import com.sicnu.consystem.Util.StatusEnum;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.websocket.OnOpen;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SwotService
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/5 22:32
 * @Version v1.0
 */
@Service
public class SwotService implements SwotServicelpml {


    @Resource
    SwotMapper swotMapper;

    @Override
    public BackFrontMessage addSwot(Swot swot) {
        List<Map<String,Object>> res=new ArrayList<>();
        List<String> advantege = swot.getAdvantege();
        List<String> disadvantege = swot.getDisadvantege();
        List<String> opportunity = swot.getOpportunity();
        List<String> threaten = swot.getThreaten();
        for (String s : advantege) {
            Map<String,Object>map=new HashMap<>();
            map.put("type","advanteeg");
            map.put("content",s);
            res.add(map);
        }
        for (String s : disadvantege) {
            Map<String,Object>map=new HashMap<>();
            map.put("type","disadvanteeg");
            map.put("content",s);
            res.add(map);
        }
        for (String s : opportunity) {
            Map<String,Object>map=new HashMap<>();
            map.put("type","opportunity");
            map.put("content",s);
            res.add(map);
        }
        for (String s : threaten) {
            Map<String,Object>map=new HashMap<>();
            map.put("type","threaten");
            map.put("content",s);
            res.add(map);
        }
        swot.setUid(UserAuthenticationUtil.getCurrentUser().getUid());
        int i = swotMapper.addSwot(swot);
        if (i!=0){
            swotMapper.addSwotContent(swot.getSid(),res);
        }
        return new BackFrontMessage(StatusEnum.SUCCESS,"创建SWOT成功");
    }

    @Override
    public BackFrontMessage getUsersAllSwot() {
        List<Swot> allSwot = swotMapper.getAllSwot(UserAuthenticationUtil.getCurrentUser().getUid());
        for (Swot swot : allSwot) {
            List<String> swotOpportunity = swotMapper.getSwotOpportunity(UserAuthenticationUtil.getCurrentUser().getUid(), swot.getSid());
            List<String> swotDisAdvantege = swotMapper.getSwotDisAdvantege(UserAuthenticationUtil.getCurrentUser().getUid(), swot.getSid());
            List<String> swotAdvantege = swotMapper.getSwotAdvantege(UserAuthenticationUtil.getCurrentUser().getUid(), swot.getSid());
            List<String> swotThreaten = swotMapper.getSwotThreaten(UserAuthenticationUtil.getCurrentUser().getUid(), swot.getSid());
            swot.setAdvantege(swotAdvantege);
            swot.setDisadvantege(swotDisAdvantege);
            swot.setOpportunity(swotOpportunity);
            swot.setThreaten(swotThreaten);
        }
        if (allSwot!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,allSwot);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @Override
    public BackFrontMessage useSwot(int sid, int mid) {
        Swot swot = swotMapper.getSwotByid(sid);
        int uid = UserAuthenticationUtil.getCurrentUser().getUid();
        List<String> swotAdvantege = swotMapper.getSwotAdvantege(uid, sid);
        List<String> swotDisAdvantege = swotMapper.getSwotDisAdvantege(uid, sid);
        List<String> swotOpportunity = swotMapper.getSwotOpportunity(uid, sid);
        List<String> swotThreaten = swotMapper.getSwotThreaten(uid, sid);
//        swot.setAdvantege(swotAdvantege);
//        swot.setThreaten(swotThreaten);
//        swot.setOpportunity(swotOpportunity);
//        swot.setDisadvantege(swotAdvantege);
        List<Map<String,Object>> res=new ArrayList<>();
        for (String s : swotAdvantege) {
            Map<String,Object>map=new HashMap<>();
            map.put("type","advantege");
            map.put("content",s);
            res.add(map);
        }
        for (String s : swotDisAdvantege) {
            Map<String,Object>map=new HashMap<>();
            map.put("type","disadvantege");
            map.put("content",s);
            res.add(map);
        }
        for (String s : swotOpportunity) {
            Map<String,Object>map=new HashMap<>();
            map.put("type","opportunity");
            map.put("content",s);
            res.add(map);
        }
        for (String s : swotThreaten) {
            Map<String,Object>map=new HashMap<>();
            map.put("type","threaten");
            map.put("content",s);
            res.add(map);
        }
        int i = swotMapper.addSwotAnswer(swot.getSid(), mid, uid, res);
        return null;
    }

    @Override
    public BackFrontMessage addSwotAnswer(int mid, int sid, List<String> advantege, List<String> disadvantege, List<String> opportunity, List<String> threaten) {
        List<Map<String,Object>> res=new ArrayList<>();
        if (advantege.size()!=0){
            for (String s : advantege) {
                Map<String,Object>map=new HashMap<>();
                map.put("type","advantege");
                map.put("content",s);
                res.add(map);
            }
        }
        if (disadvantege.size()!=0){
            for (String s : disadvantege) {
                Map<String,Object>map=new HashMap<>();
                map.put("type","disadvantege");
                map.put("content",s);
                res.add(map);
            }
        }
        if (opportunity.size()!=0){
            for (String s : opportunity) {
                Map<String,Object>map=new HashMap<>();
                map.put("type","opportunity");
                map.put("content",s);
                res.add(map);
            }
        }
        if (threaten.size()!=0){
            for (String s : threaten) {
                Map<String,Object>map=new HashMap<>();
                map.put("type","threaten");
                map.put("content",s);
                res.add(map);
            }
        }
        if (res.size()!=0){
            int i = swotMapper.addSwotAnswer(sid, mid, UserAuthenticationUtil.getCurrentUser().getUid(), res);
            if (i!=1){
                return new BackFrontMessage(StatusEnum.SUCCESS,null);
            }
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

    @Override
    public BackFrontMessage getSwotAnswer(int mid, int sid) {
        Swot swot=swotMapper.getSwotByid(sid);
        List<String> swotAnsewerAdvantege = swotMapper.getSwotAnsewerAdvantege(mid, sid);
        List<String> swotAnsewerDisAdvantege = swotMapper.getSwotAnsewerDisAdvantege(mid, sid);
        List<String> swotAnsewerOpportunity = swotMapper.getSwotAnsewerOpportunity(mid, sid);
        List<String> swotAnsewerThreaten = swotMapper.getSwotAnsewerThreaten(mid, sid);
        swot.setAdvantege(swotAnsewerAdvantege);
        swot.setDisadvantege(swotAnsewerDisAdvantege);
        swot.setOpportunity(swotAnsewerOpportunity);
        swot.setThreaten(swotAnsewerThreaten);


        System.out.println("swotAnsewerAdvantege = " + swotAnsewerAdvantege);
        System.out.println("swotAnsewerDisAdvantege = " + swotAnsewerDisAdvantege);
        if (swot!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,"",swot);
        }
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }
}

