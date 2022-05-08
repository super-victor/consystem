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

    List<String>getSwotAdvantege(int uid,int sid);
    List<String>getSwotDisAdvantege(int uid,int sid);
    List<String>getSwotOpportunity(int uid,int sid);
    List<String>getSwotThreaten(int uid,int sid);

}
