package com.sicnu.consystem.Service;
import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Mapper.HypothMapper;
import com.sicnu.consystem.Pojo.Hypoth;
import com.sicnu.consystem.Service.Servicelpml.HypothServicelmpl;
import com.sicnu.consystem.Util.StatusEnum;
import com.sicnu.consystem.Util.UserAuthenticationUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}

