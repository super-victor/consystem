package com.sicnu.consystem.Controller;

import com.sicnu.consystem.Json.BackFrontMessage;
import com.sicnu.consystem.Util.File.FileUtil;
import com.sicnu.consystem.Util.File.nativeFileUtil;
import com.sicnu.consystem.Util.StatusEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

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

        String s = fileUtil.saveFile(file);
        return new BackFrontMessage(StatusEnum.FIAL,null);
    }

}

