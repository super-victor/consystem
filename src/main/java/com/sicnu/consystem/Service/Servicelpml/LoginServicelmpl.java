package com.sicnu.consystem.Service.Servicelpml;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Pojo.User;

/**
 * @InterfaceName LoginServicelmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2022/2/27 20:48
 * @Version v1.0
 */

public interface LoginServicelmpl {

    User login(String username, String password);
}
