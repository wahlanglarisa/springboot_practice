package com.larisa.config;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSADecryptUtil {
    public static String decrypt(String encryptedPassword) throws Exception {

        PrivateKey privateKey = RSAUtil.getPrivateKey();
        PublicKey publicKey=RSAUtil.getPublicKeyObject();
        System.out.println(encryptedPassword);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        System.out.println("Public Key "+publicKey.getFormat()+" Private key "+privateKey.getFormat());
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));

        return new String(decryptedBytes);
    }
}
