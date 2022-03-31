package com.sicnu.consystem.Util.File;

import com.sicnu.consystem.Util.Exception.FileNotFoundException;
import com.sicnu.consystem.Util.Exception.FileOptionFailureException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @InterfaceName FileUtil
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/20 12:01
 * @Version v1.0
 */

public interface FileUtil {
    String saveFile(MultipartFile multipartFile) throws IOException;
    boolean deleteFile(String path);
    String doowLoadFile(HttpServletResponse response, String filename) throws FileNotFoundException, FileOptionFailureException;
}
