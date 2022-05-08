package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.Sunmmary;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName SunmmaryMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/8 12:55
 * @Version v1.0
 */
@Mapper
public interface SunmmaryMapper {

    int addSunmmary(Sunmmary sunmmary);

    Sunmmary getSunmmary(int mid);

    int updateSunmmary(Sunmmary sunmmary);
}
