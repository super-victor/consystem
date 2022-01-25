package com.sicnu.consystem.Pojo;

import lombok.Data;

/**
 * @ClassName Employee
 * @Description
 * @Author pan
 * @LastChangeDate 2022/1/8 19:09
 * @Version v1.0
 */
@Data
/*
   电脑自带的键盘敲击起来还是很舒服的。
   只是有时候不是很方便
   修改
   还需要加以改进
 */

public class Employee {
    public int id;
    public String name;
    public int salary;
    public int departmentId;
}

