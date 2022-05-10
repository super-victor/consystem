package com.sicnu.consystem.Service;
import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.HypothMapper;
import com.sicnu.consystem.Pojo.Hypoth;
import com.sicnu.consystem.Service.Servicelpml.HypothServicelmpl;
import com.sicnu.consystem.Util.StatusEnum;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;
import sun.text.normalizer.UBiDiProps;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HypotyService
 * @Description
 * @Author pan
 * @LastChangeDate 2022/5/3 23:02
 * @Version v1.0
 */
@Service
public class HypotyService implements HypothServicelmpl {

    @Resource
    HypothMapper hypothMapper;

    @Override
    public BackFrontMessage AddHypoth(Hypoth hypoth) {
        int uid = UserAuthenticationUtil.getCurrentUser().getUid();
        hypoth.setUid(uid);
        int i = hypothMapper.addHypoty(hypoth);
        if (i!=0){
            return new BackFrontMessage(StatusEnum.SUCCESS,"新增假设成功");
        }
        return new BackFrontMessage(StatusEnum.FIAL,"新增假设失败");
    }

    @Override
    public BackFrontMessage getAllHypoth() {
        List<Hypoth> allHypoth = hypothMapper.getAllHypoth();
        if (allHypoth!=null){
            return new BackFrontMessage(StatusEnum.SUCCESS,"获取所有假设成功",allHypoth);
        }
        return new BackFrontMessage(StatusEnum.FIAL,"获取所有假设失败",null);
    }

    @Override
    public BackFrontMessage updateHypoth(Hypoth hypoth) {
        int i = hypothMapper.updateHypoth(hypoth);
        if (i!=0){
            return new BackFrontMessage(StatusEnum.SUCCESS,"修改假设成功",null);
        }
        return new BackFrontMessage(StatusEnum.FIAL,"修改假设失败",null);
    }

    @Override
    public BackFrontMessage deleteHypoth(int hid) {
        int i = hypothMapper.deleteHypoth(hid);
        if (i!=0){
            return new BackFrontMessage(StatusEnum.SUCCESS,"删除假设成功",null);
        }
        return new BackFrontMessage(StatusEnum.FIAL,"删除假设失败",null);
    }

    @Override
    public BackFrontMessage giveHypothAnswer(int hid, int uid, int answer,int mid) {
        int hypothAnswer = hypothMapper.isHypothAnswer(hid, uid,mid);
        if (hypothAnswer!=0){
            return new BackFrontMessage(400,"请勿重复作答",null);
        }
        int i = hypothMapper.addHypothAnswer(hid, uid, answer,mid);
        if (i!=0){
            return new BackFrontMessage(StatusEnum.SUCCESS,"作答成功",null);
        }
        return new BackFrontMessage(StatusEnum.FIAL,"作答失败",null);
    }

    @Override
    public BackFrontMessage HypothDetails(int mid) {
        List<Hypoth> allHypothByMid = hypothMapper.getAllHypothByMid(mid);
        List<Object>res=new ArrayList<>();
        for (Hypoth hypoth : allHypothByMid) {
            Map<String,Object>h=new HashMap<>();
            h.put("hypoth",hypoth);
            int disAgreeNum = hypothMapper.getDisAgreeNum(hypoth.getHid(), mid);
            int agreeNum = hypothMapper.getAgreeNum(hypoth.getHid(), mid);
            h.put("agree",agreeNum);
            h.put("diagree",disAgreeNum);
            res.add(h);
        }
        return new BackFrontMessage(StatusEnum.SUCCESS,"",res);
    }
}

