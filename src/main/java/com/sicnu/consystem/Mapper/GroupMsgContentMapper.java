package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.GroupMsgContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName GroupMsgContentMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/7 14:37
 * @Version v1.0
 */
@Mapper
public interface GroupMsgContentMapper {
    int addGroupMsgContent(GroupMsgContent groupMsgContent);

    List<GroupMsgContent> getAllGroupMsgContent(int mid);
}
