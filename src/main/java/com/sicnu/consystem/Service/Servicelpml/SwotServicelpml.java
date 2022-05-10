package com.sicnu.consystem.Service.Servicelpml;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.Swot;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    BackFrontMessage useSwot(int sid,int mid);

    BackFrontMessage addSwotAnswer(int mid,int sid, List<String> advantege, List<String>disadvantege,
                                    List<String> opportunity,
                                    List<String> threaten);

    BackFrontMessage getSwotAnswer(int mid,int sid);
}

