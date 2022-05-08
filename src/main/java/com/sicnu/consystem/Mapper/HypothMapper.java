package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.Hypoth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName HypothMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/5/3 23:00
 * @Version v1.0
 */

@Mapper
public interface HypothMapper {
    int addHypoty(Hypoth hypoth);

    List<Hypoth>getAllHypoth();

    int updateHypoth(Hypoth hypoth);

    int deleteHypoth(int hid);
}

