package com.sicnu.consystem.Service.Servicelpml;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.Swot;

/**
 * @ClassName SwotServicelpml
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/5 22:31
 * @Version v1.0
 */

public interface SwotServicelpml {

    BackFrontMessage addSwot(Swot swot);

    BackFrontMessage getUsersAllSwot();
}

