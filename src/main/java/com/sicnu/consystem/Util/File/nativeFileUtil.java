package com.sicnu.consystem.Util.File;

import com.sicnu.consystem.Util.Exception.FileNotFoundException;
import com.sicnu.consystem.Util.Exception.FileOptionFailureException;
import com.sicnu.consystem.Util.ServerConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.sicnu.consystem.Util.Exception.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ClassName nativeFileUtil
 * @Description
 * @Author pan
 * @LastChangeDate 2022/3/20 12:02
 * @Version v1.0
 */
@Component
public class nativeFileUtil implements FileUtil{

    @Resource
    ServerConfig serverConfig;

    public final static String localPath="D:\\Learning Materials\\web开发\\consystem\\src\\main\\resources\\static";

    public String getLocalPath(){
        return localPath;
    }

    public String getNativeAvatarurl(String avatarurl){
        return serverConfig.getStaticResouceUrl()+avatarurl.substring(avatarurl.lastIndexOf("\\",avatarurl.length()));
    }

    @Override
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String res=null;
        File newfile=new File(localPath+"/"+multipartFile.getOriginalFilename());
        if (!newfile.exists()){
            newfile.mkdir();
            multipartFile.transferTo(newfile);
        }else {
            multipartFile.transferTo(newfile);
        }
        res=newfile.getAbsolutePath();
        return res;
    }

    @Override
    public boolean deleteFile(String path) {
        File oldFile=new File(path);
        if (oldFile.exists()){
            oldFile.delete();
            return true;
        }
        return false;
    }

    @Override
    public String doowLoadFile(HttpServletResponse response, String filename) throws FileNotFoundException, FileOptionFailureException {
        File file=new File(localPath+"/"+filename);
        if (!file.exists()){
            throw new FileNotFoundException("文件未找到");
        }else {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            response.setHeader("Content-Disposition", "attachment;filename="+filename);
            try {
                byte buff[]=new byte[1024];
                BufferedInputStream bufferedInputStream=new BufferedInputStream(new FileInputStream(file));
                OutputStream os=response.getOutputStream();
                int i=0;
                while ((i=bufferedInputStream.read(buff))!=-1){
                    os.write(buff,0,i);
                    os.flush();
                }
                os.close();
                bufferedInputStream.close();
                return "成功";
            }catch (Exception e){
                throw new FileOptionFailureException("文件操作失败");
            }
        }
    }

}

