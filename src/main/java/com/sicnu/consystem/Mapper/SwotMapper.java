package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.Swot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName SwotMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/5 15:36
 * @Version v1.0
 */
@Mapper
public interface SwotMapper {
    int addSwot(Swot swot);

    void addSwotContent(int sid,List<Map<String,Object>>maplist);

    List<Swot>getAllSwot(int uid);


    Swot getSwotByid(int sid);

    List<String>getSwotAdvantege(int uid,int sid);
    List<String>getSwotDisAdvantege(int uid,int sid);
    List<String>getSwotOpportunity(int uid,int sid);
    List<String>getSwotThreaten(int uid,int sid);

    List<String>getSwotAnsewerAdvantege(int mid,int sid);
    List<String>getSwotAnsewerDisAdvantege(int mid,int sid);
    List<String>getSwotAnsewerOpportunity(int mid,int sid);
    List<String>getSwotAnsewerThreaten(int mid,int sid);

    int addSwotAnswer(int sid,int mid,int uid,List<Map<String,Object>>maplist);


}
