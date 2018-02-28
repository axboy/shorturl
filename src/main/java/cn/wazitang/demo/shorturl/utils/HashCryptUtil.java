package cn.wazitang.demo.shorturl.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/1/22 14:49
 */
public class HashCryptUtil {

    static String getMD5(byte[] bytes) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(bytes, 0, bytes.length);
        BigInteger bigInt = new BigInteger(1, digest.digest());
        String md5 = bigInt.toString(16);
        return md5.length() == 32 ? md5 : "0" + md5;
    }

    static String getMD5(String text) {
        if (text == null) {
            return null;
        }
        return getMD5(text.getBytes());
    }
}
