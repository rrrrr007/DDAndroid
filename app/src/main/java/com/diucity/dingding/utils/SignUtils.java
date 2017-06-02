package com.diucity.dingding.utils;

import android.util.Log;

import com.diucity.dingding.entity.Send.NormalBean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by Administrator on 2017/4/7 0007.
 */

public class SignUtils {
    public static String sign(String params) {
        NormalBean bean = GsonUtils.GsonToBean(params, NormalBean.class);
        return md5(bean.getNonce() + params + String.valueOf(bean.getTimestamp())).toUpperCase();
    }


    public static String md5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString().toUpperCase();
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId;
    }

    public static String loginCode(long time, String code) {
        Log.d("ch", "" + time);
        return md5(time + "login" + dbloginCode(code)).toUpperCase();
    }

    public static String dbloginCode(String code) {
        return md5(code).toUpperCase();
    }

    public static String payCode(long time, String code) {
        return md5(String.valueOf(time) + "pay" + code).toUpperCase();
    }

    public static String authCode(long time, String taken) {
        return md5(String.valueOf(time) + "#auth#" + taken).toUpperCase();
    }
}
