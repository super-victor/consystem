package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName LogMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/20 16:44
 * @Version v1.0
 */
@Mapper
public interface LogMapper {

    List<Log>getAllLogs();

    List<String >getAllTypes();

    List<Integer>logid();

    int deleteLog(int id);
}
