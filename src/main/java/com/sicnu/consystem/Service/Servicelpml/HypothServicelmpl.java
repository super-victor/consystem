package com.sicnu.consystem.Service.Servicelpml;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.Hypoth;

/**
 * @ClassName hypothServicelmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2022/5/3 23:02
 * @Version v1.0
 */

public interface HypothServicelmpl {
    BackFrontMessage AddHypoth(Hypoth hypoth);

    BackFrontMessage getAllHypoth();

    BackFrontMessage updateHypoth(Hypoth hypoth);

    BackFrontMessage deleteHypoth(int hid);
}

