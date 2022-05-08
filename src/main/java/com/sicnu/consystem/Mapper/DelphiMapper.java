package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.Delphi;
import com.sicnu.consystem.Pojo.DelphiAnswer;
import com.sicnu.consystem.Pojo.Questionnaire;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Queue;

/**
 * @ClassName DelphiMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/2 12:26
 * @Version v1.0
 */
@Mapper
public interface DelphiMapper {
    int addDelphi(Delphi delphi);
    List<Delphi>getAllDelphi(int uid);
//    List<Delphi> getDelphiByConDiton(Delphi delphi);
    Delphi isDelphiExsite(Delphi delphi);

    int addQuestionnaire(Questionnaire questionnaire);

    Delphi getDelphiById(int did);

    int isDelphiUsed(int did);

    int addQuestionnaireContent(int did,int dpid);

    List<Questionnaire> getAllQuestionNaire(int uid);

    int addDelphiAnswer(int uid,int mid,int dpid,String answer);

    DelphiAnswer findOneDelphiAnswer(int uid, int mid, int dpid);

    List<Questionnaire>getAllDelphiByMid(int mid);

    List<String> getAnswerByDpis(int dpid);

    int modifyDelphi(Delphi delphi);

    int deleteDelphi(int did);
}

