package com.sicnu.consystem.Controller;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Util.Exception.FileNotFoundException;
import com.sicnu.consystem.Util.Exception.FileOptionFailureException;
import com.sicnu.consystem.Util.File.FileUtil;
import com.sicnu.consystem.Util.File.nativeFileUtil;
import com.sicnu.consystem.Util.StatusEnum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FileController
 * @Description
 * @Author pan
 * @LastChangeDate 2022/4/5 18:50
 * @Version v1.0
 */

@RestController
public class FileController {

    @Resource
    nativeFileUtil fileUtil;

    @PostMapping("/uploadimage")
    public BackFrontMessage uploadimage(
            @RequestParam("file") MultipartFile file
            ){
        String nativeAvatarurl = null;
        try {
             nativeAvatarurl = fileUtil.saveImgFile(file);
            System.out.println("nativeAvatarurl = " + nativeAvatarurl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (nativeAvatarurl!=null){
                return new BackFrontMessage(StatusEnum.SUCCESS,nativeAvatarurl);
            }else {
                return new BackFrontMessage(StatusEnum.FIAL,null);
            }
        }
    }

    @PostMapping("/uploadfile")
    public BackFrontMessage uploadFile(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String MyfileInfo = fileUtil.saveFile(file);
        Map<String,String>msg=new HashMap<>();
        msg.put("fid",MyfileInfo);
        return new BackFrontMessage(StatusEnum.SUCCESS,msg);
    }

//    http://localhost:8082/static/file/\4分钟TABATA燃脂训练.mp4



    @PostMapping("/downLoadFile")
    public void downLoadFile(HttpServletResponse response,@RequestParam int fid){
        System.out.println("fid = " + fid);
        try {
            String msg = fileUtil.doowLoadFile(response, fid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

