package com.sicnu.consystem.Mapper;

import com.sicnu.consystem.Pojo.File;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName FileMapper
 * @Description
 * @Author pan
 * @LastChangeDate 2022/5/8 16:46
 * @Version v1.0
 */
@Mapper
public interface FileMapper {
    int addFile(File file);

    int getFile(File file);
    File getMyfile(String fname,String fpath,String furl);

    File getFileByFid(int fid);
}

