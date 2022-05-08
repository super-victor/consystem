package com.sicnu.consystem.Service;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.SwotMapper;
import com.sicnu.consystem.Pojo.Swot;
import com.sicnu.consystem.Service.Servicelpml.SwotServicelpml;
import com.sicnu.consystem.Util.StatusEnum;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}

