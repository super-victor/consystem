package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/2/7 9:05
 * @Version v1.0
 */
@Mapper
public interface UserMapper{
    User getUserByNameAndPwd(String username,String password);
}

