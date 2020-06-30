package com.wrpxcx.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Security;

/**
 * 微信工具类
 */
public class WeChatUtil {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String en1 = "Tg87llLeZLfYQfFFATnwvrnRWSPHTsBieDo1Q+c9tEpb4tdflWiUdj3HqccTd9f6PME37PZ9uUPXgc9Tzac3Kqtj/AFKQRRzEfnG2e8HfIzJnAXnQ2OzKp306U0R47LIgpdEvsdNZ387Z560Rmyvnh2j/cToq9kb0noZNLhkO+nEbEBGFNux+yuOaAnIinFxp9l1q46nT6qzEUOUvh38wIgCXi/j6h79th6TFJcHU2oBsJGWnh6R0DZjLlUw56e+JE5Mgl6966Tv3aWDWokJW3qEKoyW66AW9fBqOGdLqxlCOuHpO5oBtyJ7jLnvdQEDlOSRetlYE35gKYlI3Fmvx5t4msdHIVO1WpdZLvCZ0XdrRD9rQs4OlWSyKw5lkywNSStsrGwMjBCS5U8rYcfeIFs+/c2gkRhQIlg6fjlFsCvxY9BbT5v2R7rfS8peCgBE1aRofSYShunf6+4lFIgc3QlGxFkM8W8N2HLrn+DsTeo=";
        String session1 = "O07EQqCga1A/RjKd1jNgyg==";
        String iv1 = "8TqhiFheX1GJcTLRHYo+aA==";

        String result = decryptData(en1, session1, iv1);

        System.out.println("result = " + result);
    }

    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) throws UnsupportedEncodingException {

        return new String(
                decryptOfDiyIV(
                        Base64.decode(encryptDataB64),
                        Base64.decode(sessionKeyB64),
                        Base64.decode(ivB64)
                ), "utf-8"
        );
    }

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedText;
    }
}