package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    User getUserByUid(int uid);

    int userAdd(User user);

    int updateUser(User user);

    int deleteUserByUid(int uid);

    List<User> getUsersByCondition(String username, int forbid, int isAesc);

    List<User>getAllUsers();
}

