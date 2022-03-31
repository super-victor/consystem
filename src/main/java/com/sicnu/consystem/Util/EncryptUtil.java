package com.sicnu.consystem.Util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName EncryptUtil
 * @Description
 * @Author pan
 * @LastChangeDate 2022/2/27 20:32
 * @Version v1.0
 */
@Component
public class EncryptUtil {
    public String MD5(String pass)  {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(pass.getBytes());
            return new BigInteger(1,messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public String SHA(String pass){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(pass.getBytes());
            return new BigInteger(1,messageDigest.digest()).toString(32);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}

