package com.sicnu.consystem.Service.Servicelpml;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.Delphi;
import com.sicnu.consystem.Pojo.DelphiAnswer;

/**
 * @ClassName DelphiServicelpml
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/2 12:37
 * @Version v1.0
 */

public interface DelphiServicelpml {
    BackFrontMessage addDelphi(Delphi delphi);

    BackFrontMessage getAllDelphis();

    BackFrontMessage addQuestionnaire(String dpname,Integer[]dids);

    BackFrontMessage getAllQuestionNaire();

    int addDelphiAnswer(int uid,int mid,int dpid,String answer);

    DelphiAnswer findOneDelphiAnswer(int uid, int mid, int dpid);

    BackFrontMessage getDelphiDataDetails(int mid);

//    getDelphiDataDetails(int mi)
    BackFrontMessage modifyDelphi(Delphi delphi);

    BackFrontMessage deleteDelphi(int did);
}

